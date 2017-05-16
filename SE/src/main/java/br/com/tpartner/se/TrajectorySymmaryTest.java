/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.se;

import br.com.tpartner.persistence.crud.StudentCRUD;
import br.com.tpartner.persistence.model.Student;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author sergio
 */
public class TrajectorySymmaryTest {
    public static void main( String[] args ) throws ParseException, IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        StudentCRUD studentDAO = context.getBean(StudentCRUD.class);
        Student student = studentDAO.findById((long)7250179);
        SimpleDateFormat dateFormat;
        dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date timeStart = dateFormat.parse("2015-05-1 00:00:00.000");
        Date timeEnd = dateFormat.parse("2015-05-30 23:59:59.999");
        TrajectorySummary trajectorySummary = new TrajectorySummary(student, timeStart, timeEnd);
        context.close();
    }
}
