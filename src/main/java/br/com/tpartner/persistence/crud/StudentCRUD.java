/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.crud;

import br.com.tpartner.persistence.model.Student;
import br.com.tpartner.persistence.model.TrajectorySummariesRequest;
import br.com.tpartner.persistence.model.TrajectorySummary;
import java.util.List;

/**
 *
 * @author sergio
 */
public interface StudentCRUD extends GenericCRUD<Student, Long> {

    public List<TrajectorySummary> getSummaries(
            TrajectorySummariesRequest trajectorySummariesRequest);
    
}

