/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.se;

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
import java.text.DateFormat;
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
        csvMatrix = reader.getMatrix("/home/sergio/Downloads/logs.csv", ";", "iso-8859-1");
        
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
    
    private Action getAction(List<String> line) throws ParseException{
        String timeString = line.get(2);
        DateFormat dateFormat = getTimestampType(timeString);
        Date time = dateFormat.parse(timeString);
        Action action;
        action = new Action(time, line.get(1), line.get(3), line.get(4), 
                line.get(5), line.get(6), line.get(7), line.get(8), 
                line.get(9), line.get(10), line.get(11), line.get(12),
                line.get(13), line.get(14), line.get(15), line.get(16),
                line.get(17), line.get(18), line.get(19), line.get(20),
                line.get(21), line.get(22), line.get(23), line.get(24));
        return action;
    }
    
    private void createActions(List<List<String>> csvMatrix) throws ParseException{
        for (List<String> line : csvMatrix) {
            /* needs to identify the user to assign the action */
            Action action = getAction(line);
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
