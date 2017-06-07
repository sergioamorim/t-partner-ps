/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.se;

import br.com.tpartner.persistence.crud.AccessSessionCRUD;
import br.com.tpartner.persistence.crud.EducationalResourceCRUD;
import br.com.tpartner.persistence.crud.StudentCRUD;
import br.com.tpartner.persistence.model.AccessSession;
import br.com.tpartner.persistence.model.StudentAction;
import br.com.tpartner.persistence.model.Student;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import br.com.tpartner.persistence.crud.StudentActionCRUD;
import br.com.tpartner.persistence.crud.SubSessionCRUD;
import br.com.tpartner.persistence.model.EducationalResource;
import br.com.tpartner.persistence.model.NonMappedStudentAction;
import br.com.tpartner.persistence.model.ProblemSolving;
import br.com.tpartner.persistence.model.ResourceInteraction;
import br.com.tpartner.persistence.model.SubSession;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sergio
 */
public class CSVReader {
    
    private static final Integer SUB_SESSION_TIME_LIMIT = 180000; //milliseconds
    private static final Map<String, Integer> CSV_FILE_HEADER =
        new HashMap<String, Integer>() {
            {
                put("STUDENT_ID", 0);
                put("LOG_TYPE", 1);
                put("TIME_STAMP", 2);
                put("PROBLEM_ID", 3);
                put("PROBLEM_CORRECTLY_DONE", 4);
                put("PROBLEM_RESPONSE_TIME", 5);
                put("CONTENT_ID", 6);
                put("CONTENT_VIEW_TIME", 7);
                put("LGOAL_CURRICULUM", 8);
                put("LGOAL_VALUE", 9);
                put("DGOAL_DOMAIN", 10);
                put("DGOAL_VALUE", 11);
                put("GAMIFICATION_LEVEL", 12);
                put("NUMBER_OF_POINTS", 13);
                put("RS_ID", 14);
                put("RS_TYPE", 15);
                put("RS_COMPLETED", 16);
                put("RS_N_CORRECT", 17);
                put("RS_N_RESOURCES", 18);
                put("PBE_RESPONSE_TIME", 19);
                put("PBE_ABILITY_SCORE", 20);
                put("PBE_ABSOLUTE_SCORE", 21);
                put("PBE_NUM_CORRECT", 22);
                put("PBE_NUM_BLANK", 23);
                put("PBE_NUM_WRONG", 24);
                put("ACTIVITY_LOOP_ID", 25);
            }
    };
    
    
    public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException, ParseException {
        CSVReader reader;
        reader = new CSVReader();
        List<List<String>> csvMatrix;
        csvMatrix = reader.getMatrix("/home/sergio/Documents/logs-ready.csv", ",", "iso-8859-1");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        reader.run(context, csvMatrix);
        context.close();
        System.out.println("OK");
    }
    
    private void run(ClassPathXmlApplicationContext context, List<List<String>> csvMatrix) throws ParseException {
        StudentCRUD studentDAO = context.getBean(StudentCRUD.class);
        this.createStudents(csvMatrix, studentDAO);
        AccessSessionCRUD accessSessionDAO = context.getBean(AccessSessionCRUD.class);
        this.createAccessSessions(csvMatrix, accessSessionDAO, studentDAO);
        SubSessionCRUD subSessionDAO = context.getBean(SubSessionCRUD.class);
        StudentActionCRUD studentActionDAO = context.getBean(StudentActionCRUD.class);
        EducationalResourceCRUD educationalResourceDAO = context.getBean(EducationalResourceCRUD.class);
        this.createStudentActions(csvMatrix, studentActionDAO, studentDAO, accessSessionDAO, subSessionDAO, educationalResourceDAO);
    }
    
    private SimpleDateFormat getTimestampType(String timeString){
        SimpleDateFormat dateFormat;
        if (timeString.charAt(2) == '/'){
            dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        }
        else {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        }
        return dateFormat;
    }
    
    private Date getDateTime(List<String> line) throws ParseException {
        String timeString = line.get(CSV_FILE_HEADER.get("TIME_STAMP"));
        SimpleDateFormat dateFormat = getTimestampType(timeString);
        Date dateTime = dateFormat.parse(timeString);
        return dateTime;
    }
    
    private EducationalResource getEducationalResource(
            String educationalResourceId,
            EducationalResourceCRUD educationalResourceDAO) {
        
        EducationalResource educationalResource;
        educationalResource =
                educationalResourceDAO.findById(educationalResourceId);
        
        if (educationalResource == null) {
            educationalResource =
                    new EducationalResource(educationalResourceId);
            educationalResourceDAO.save(educationalResource);
            educationalResource =
                    educationalResourceDAO.findById(educationalResourceId);
        }
        
        return educationalResource;
    }
    
    private StudentAction getStudentAction(List<String> line,
            SubSession subSession,
            EducationalResourceCRUD educationalResourceDAO)
            throws ParseException {
        
        Date dateTime = getDateTime(line);
        StudentAction studentAction;
        
        if (line.get(CSV_FILE_HEADER.get("LOG_TYPE")).equals(
                "PROBLEM_SOLVING")) {
            
            EducationalResource educationalResource = getEducationalResource(
                    line.get(CSV_FILE_HEADER.get("PROBLEM_ID")),
                    educationalResourceDAO);
                    
            Boolean correctlyDone = line.get(CSV_FILE_HEADER.get(
                    "PROBLEM_CORRECTLY_DONE")).equals("1");
            
            Integer timeSpent = Integer.parseInt(line.get(CSV_FILE_HEADER.get(
                    "PROBLEM_RESPONSE_TIME")));
            
            studentAction =
                    new ProblemSolving(subSession, dateTime,
                            educationalResource, correctlyDone, timeSpent);
        
        }
        
        else if (line.get(CSV_FILE_HEADER.get("LOG_TYPE")).equals(
                "CONTENT_VIEW")) {
        
            EducationalResource educationalResource = getEducationalResource(
                    line.get(CSV_FILE_HEADER.get("CONTENT_ID")),
                    educationalResourceDAO);
            
            Integer timeSpent = Integer.parseInt(line.get(CSV_FILE_HEADER.get(
                    "CONTENT_VIEW_TIME")));
            
            studentAction = new ResourceInteraction(subSession, dateTime,
                    educationalResource, timeSpent);
            
        }
        else {
            studentAction = new NonMappedStudentAction(subSession, dateTime,
                    line.get(CSV_FILE_HEADER.get("LOG_TYPE")),
                    line.get(CSV_FILE_HEADER.get("LGOAL_CURRICULUM")),
                    line.get(CSV_FILE_HEADER.get("LGOAL_VALUE")),
                    line.get(CSV_FILE_HEADER.get("DGOAL_DOMAIN")),
                    line.get(CSV_FILE_HEADER.get("DGOAL_VALUE")),
                    line.get(CSV_FILE_HEADER.get("GAMIFICATION_LEVEL")),
                    line.get(CSV_FILE_HEADER.get("NUMBER_OF_POINTS")),
                    line.get(CSV_FILE_HEADER.get("RS_ID")),
                    line.get(CSV_FILE_HEADER.get("RS_TYPE")),
                    line.get(CSV_FILE_HEADER.get("RS_COMPLETED")),
                    line.get(CSV_FILE_HEADER.get("RS_N_CORRECT")),
                    line.get(CSV_FILE_HEADER.get("RS_N_RESOURCES")),
                    line.get(CSV_FILE_HEADER.get("PBE_RESPONSE_TIME")),
                    line.get(CSV_FILE_HEADER.get("PBE_ABILITY_SCORE")),
                    line.get(CSV_FILE_HEADER.get("PBE_ABSOLUTE_SCORE")),
                    line.get(CSV_FILE_HEADER.get("PBE_NUM_CORRECT")),
                    line.get(CSV_FILE_HEADER.get("PBE_NUM_BLANK")),
                    line.get(CSV_FILE_HEADER.get("PBE_NUM_WRONG")),
                    line.get(CSV_FILE_HEADER.get("ACTIVITY_LOOP_ID"))
            );
        }
        return studentAction;
    }
    
    private Long getStudentId(String studentIdString) {
        if (studentIdString.contains("UserAccount_"))
            return Long.parseLong(studentIdString.replace("UserAccount_", ""));
        return null;
    }
    
    private Student getStudent(List<String> line, StudentCRUD studentDAO) {        
        String studentIdString = line.get(CSV_FILE_HEADER.get("STUDENT_ID"));
        
        if (studentIdString == null) {
            return null;
        }
        
        Long studentId = this.getStudentId(studentIdString);
        
        if(studentId == null) {
            return null;
        }
        
        Student student;
        student = studentDAO.findById(studentId);
        
        if (student == null) {
            student = new Student(studentId);
            studentDAO.save(student);
            student = studentDAO.findById(studentId);
        }
        
        return student;
    }
    
    private AccessSession getAccessSession(List<String> line, AccessSessionCRUD accessSessionDAO, Student student) throws ParseException {
        Date dateTime = getDateTime(line);
        List<AccessSession> accessSessions = accessSessionDAO.findByStudent(student);
        return getNearestAccessSession(accessSessions, dateTime);
    }
    
    
    private void createStudents(List<List<String>> csvMatrix, StudentCRUD studentDAO) {
        for (List<String> line : csvMatrix) {
            getStudent(line, studentDAO);
        }
    }
    
    private void createAccessSessions(List<List<String>> csvMatrix, AccessSessionCRUD accessSessionDAO, StudentCRUD studentDAO) throws ParseException {
        for (List<String> line : csvMatrix) {
            if (line.get(CSV_FILE_HEADER.get("LOG_TYPE")).contains("USER_SESSION")&&!line.get(CSV_FILE_HEADER.get("STUDENT_ID")).contains("STUDENT_ID")) {
                Student student = getStudent(line, studentDAO);
                AccessSession accessSession = new AccessSession(student, getDateTime(line));
                accessSessionDAO.save(accessSession);
            }
        }
    }
    
    private AccessSession getNearestAccessSession(List<AccessSession> accessSessions, Date dateTime) {
        AccessSession nearestAccessSession = null;
        Integer nearestComparation = Integer.MAX_VALUE;
        for (AccessSession accessSession : accessSessions) {
            if (accessSession.getTimeStart().compareTo(dateTime) >= 0) {
                if (accessSession.getTimeStart().compareTo(dateTime) < nearestComparation) {
                    nearestComparation = accessSession.getTimeStart().compareTo(dateTime);
                    nearestAccessSession = accessSession;
                }
            }
        }
        return nearestAccessSession;
    }
    
    private SubSession getSubSession(List<String> line, SubSessionCRUD subSessionDAO, AccessSession accessSession, StudentActionCRUD studentActionDAO) throws ParseException {
        List<SubSession> subSessions = subSessionDAO.findByAccessSession(accessSession);
        for (SubSession subSession : subSessions) {
            List<StudentAction> studentActions = studentActionDAO.findBySubSession(subSession);
            for (StudentAction studentAction : studentActions) {
                Long diff = studentAction.getTime().getTime() - getDateTime(line).getTime();
                if (diff > -SUB_SESSION_TIME_LIMIT &&  diff < SUB_SESSION_TIME_LIMIT) {
                    return subSession;
                }
            }
        }
        SubSession subSession = new SubSession(getDateTime(line), accessSession);
        return subSessionDAO.save(subSession);
    }
    
    private void createStudentActions(List<List<String>> csvMatrix, StudentActionCRUD studentActionDAO, StudentCRUD studentDAO, AccessSessionCRUD accessSessionDAO, SubSessionCRUD subSessionDAO, EducationalResourceCRUD educationalResourceDAO) throws ParseException{
        for (List<String> line : csvMatrix) {
            if (!line.get(CSV_FILE_HEADER.get("LOG_TYPE")).contains("USER_SESSION")&&!line.get(CSV_FILE_HEADER.get("STUDENT_ID")).contains("STUDENT_ID")) {
                Student student = getStudent(line, studentDAO);
                AccessSession accessSession = getAccessSession(line, accessSessionDAO, student);
                if (accessSession != null) {
                    SubSession subSession = getSubSession(line, subSessionDAO, accessSession, studentActionDAO);
                    StudentAction studentAction = getStudentAction(line, subSession, educationalResourceDAO);
                    studentActionDAO.save(studentAction);
                }
            }
        }
    }
    
    /**
     *
     * @param csvFilePath
     * @param csvSeparator
     * @param encoding
     * @return
     * @throws UnsupportedEncodingException
     * @throws FileNotFoundException
     */
    private List<List<String>> getMatrix (String csvFilePath, String csvSeparator, String encoding) throws UnsupportedEncodingException, FileNotFoundException {
        
        List<List<String>> csvMatrix;
        csvMatrix = new ArrayList<List<String>>();
        
        List<String> csvLineArray;
        
	BufferedReader reader;
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(csvFilePath), encoding));
        
        String[] columns;
	String line;
        
        try {
            while ((line = reader.readLine()) != null) {
                csvLineArray = new ArrayList<String>();
                columns = line.split(csvSeparator);
                csvLineArray.addAll(Arrays.asList(columns));
                csvMatrix.add(csvLineArray);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return csvMatrix;
    }
}
