/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.se;

import br.com.tpartner.model.se.Action;
import br.com.tpartner.model.se.SessionA;
import br.com.tpartner.model.se.Student;
import br.com.tpartner.repository.classes.se.StudentRepository;
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
        csvMatrix = reader.getMatrix("C:\\Users\\sergi\\Downloads\\logs.csv", ";", "iso-8859-1");
        reader.createStudents(csvMatrix);
        reader.createSessionsA(csvMatrix);
        reader.createActions(csvMatrix);
        System.out.println("OK");
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
    
    private Action getAction(List<String> line) throws ParseException{
        Date dateTime = getDateTime(line);
        Action action;
        action = new Action(dateTime, line.get(1), line.get(3), line.get(4), 
                line.get(5), line.get(6), line.get(7), line.get(8), 
                line.get(9), line.get(10), line.get(11), line.get(12),
                line.get(13), line.get(14), line.get(15), line.get(16),
                line.get(17), line.get(18), line.get(19), line.get(20),
                line.get(21), line.get(22), line.get(23), line.get(24));
        return action;
    }
    
    private Student getStudent(List<String> line) {
        StudentRepository studentRepository = new StudentRepository();
        Student student;
        try {
            student = studentRepository.findById(line.get(0));
        }
        catch (Exception e) {
            student = new Student(line.get(0));
        }
        return student;
    }
    
    private SessionA getSessionA(List<String> line) throws ParseException {
        Student student = getStudent(line);
        Date dateTime = getDateTime(line);
        List<SessionA> sessionsA = student.getSessionsA();
        return getNearestSessionA(sessionsA, dateTime);
    }
    
    
    private void createStudents(List<List<String>> csvMatrix) {
        for (List<String> line : csvMatrix) {
            getStudent(line);
        }
    }
    
    private void createSessionsA(List<List<String>> csvMatrix) throws ParseException {
        for (List<String> line : csvMatrix) {
            if (line.get(1).contains("USER_SESSION")) {
                Student student = getStudent(line);
                List<SessionA> sessionsA = student.getSessionsA();
                SessionA sessionA = new SessionA(getDateTime(line));
                if (!sessionsA.contains(sessionA)) {
                    sessionsA.add(sessionA);
                    student.setSessionsA(sessionsA);
                }
            }
        }
    }
    
    private SessionA getNearestSessionA(List<SessionA> sessionsA, Date dateTime) {
        SessionA nearestSessionA = null;
        Integer nearestComparation = Integer.MAX_VALUE;
        for (SessionA sessionA : sessionsA) {
            if (sessionA.getTimeStart().compareTo(dateTime) >= 0) {
                if (sessionA.getTimeStart().compareTo(dateTime) < nearestComparation) {
                    nearestComparation = sessionA.getTimeStart().compareTo(dateTime);
                    nearestSessionA = sessionA;
                }
            }
        }
        return nearestSessionA;
    }
    
    private void createActions(List<List<String>> csvMatrix) throws ParseException{
        for (List<String> line : csvMatrix) {
            if (!line.get(1).contains("USER_SESSION")&&!line.get(0).contains("STUDENT_ID")) {
                SessionA sessionA = getSessionA(line);
                Action action = getAction(line);
                if (!sessionA.getActions().contains(action)) {
                    List<Action> actions = sessionA.getActions();
                    actions.add(action);
                    sessionA.setActions(actions);
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
        csvMatrix = new ArrayList<>();
        
        List<String> csvLineArray;
        
	BufferedReader reader;
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(csvFilePath), encoding));
        
        String[] columns;
	String line;
        
        try {
            while ((line = reader.readLine()) != null) {
                csvLineArray = new ArrayList<>();
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
