/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.se;

import br.com.tpartner.persistence.crud.AccessSessionCRUD;
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
import br.com.tpartner.persistence.model.SubSession;

/**
 *
 * @author sergio
 */
public class CSVReader {
    /* Testes */
    public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException, ParseException {
        CSVReader reader;
        reader = new CSVReader();
        List<List<String>> csvMatrix;
        csvMatrix = reader.getMatrix("/home/sergio/Documents/logs.csv", ";", "iso-8859-1");
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
        this.createStudentActions(csvMatrix, studentActionDAO, studentDAO, accessSessionDAO, subSessionDAO);
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
        SimpleDateFormat dateFormat = getTimestampType(line.get(2));
        Date dateTime = dateFormat.parse(line.get(2));
        return dateTime;
    }
    
    private StudentAction getStudentAction(List<String> line, SubSession subSession) throws ParseException{
        Date dateTime = getDateTime(line);
        StudentAction studentAction;
        studentAction = new StudentAction(subSession, dateTime, line.get(1), line.get(3), line.get(4), 
                line.get(5), line.get(6), line.get(7), line.get(8), 
                line.get(9), line.get(10), line.get(11), line.get(12),
                line.get(13), line.get(14), line.get(15), line.get(16),
                line.get(17), line.get(18), line.get(19), line.get(20),
                line.get(21), line.get(22), line.get(23), line.get(24));
        return studentAction;
    }
    
    private Long getStudentId(String studentIdString) {
        if (studentIdString.contains("UserAccount_"))
            return Long.parseLong(studentIdString.replace("UserAccount_", ""));
        return null;
    }
    
    private Student getStudent(List<String> line, StudentCRUD studentDAO) {
        if (this.getStudentId(line.get(0)) == null)
            return null;
        Student student;
        student = studentDAO.findById(this.getStudentId(line.get(0)));
        if (student == null) {
            student = new Student(this.getStudentId(line.get(0)));
            studentDAO.save(student);
            student = studentDAO.findById(this.getStudentId(line.get(0)));
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
            if (line.get(1).contains("USER_SESSION")&&!line.get(0).contains("STUDENT_ID")) {
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
                    System.out.println("0");
                }
            }
        }
        if (nearestAccessSession == null)
            System.out.println("1");
        return nearestAccessSession;
    }
    
    private SubSession getSubSession(List<String> line, SubSessionCRUD subSessionDAO, AccessSession accessSession, StudentActionCRUD studentActionDAO) throws ParseException {
        List<SubSession> subSessions = subSessionDAO.findByAccessSession(accessSession);
        for (SubSession subSession : subSessions) {
            List<StudentAction> studentActions = studentActionDAO.findBySubSession(subSession);
            for (StudentAction studentAction : studentActions) {
                Long diff = studentAction.getTime().getTime() - getDateTime(line).getTime();
                if (diff > -1800000 ||  diff < 1800000) {
                    return subSession;
                }
            }
        }
        SubSession subSession = new SubSession(getDateTime(line), accessSession);
        return subSessionDAO.save(subSession);
    }
    
    private void createStudentActions(List<List<String>> csvMatrix, StudentActionCRUD studentActionDAO, StudentCRUD studentDAO, AccessSessionCRUD accessSessionDAO, SubSessionCRUD subSessionDAO) throws ParseException{
        for (List<String> line : csvMatrix) {
            if (!line.get(1).contains("USER_SESSION")&&!line.get(0).contains("STUDENT_ID")) {
                Student student = getStudent(line, studentDAO);
                AccessSession accessSession = getAccessSession(line, accessSessionDAO, student);
                if (accessSession != null) {
                    SubSession subSession = getSubSession(line, subSessionDAO, accessSession, studentActionDAO);
                    StudentAction studentAction = getStudentAction(line, subSession);
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
