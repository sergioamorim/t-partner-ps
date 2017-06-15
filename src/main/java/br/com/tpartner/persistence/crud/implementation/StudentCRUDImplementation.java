/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.crud.implementation;

import br.com.tpartner.persistence.crud.StudentCRUD;
import br.com.tpartner.persistence.model.Student;
import br.com.tpartner.persistence.model.TrajectorySummariesRequest;
import br.com.tpartner.persistence.model.TrajectorySummary;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sergio
 */
@Repository
public class StudentCRUDImplementation 
        extends GenericCRUDImplementation<Student, Long>
        implements StudentCRUD {
    
    @Override
    public List<TrajectorySummary> getSummaries(
            TrajectorySummariesRequest trajectorySummariesRequest){
        List<TrajectorySummary> trajectorySummaries;
        trajectorySummaries = new ArrayList<TrajectorySummary>();
        for (Student student : trajectorySummariesRequest.getStudents()) {
            try {
                trajectorySummaries.add(new TrajectorySummary(student,
                        trajectorySummariesRequest.getStartDate(),
                        trajectorySummariesRequest.getEndDate()));
            } catch (IOException ex) {
                Logger.getLogger(StudentCRUDImplementation.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
        return trajectorySummaries;
    }
    
}
