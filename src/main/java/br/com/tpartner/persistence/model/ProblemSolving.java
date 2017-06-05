/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author sergio
 */
@Entity
@Table(name = "problem_solving")
public class ProblemSolving extends ResourceInteraction {
    
    private final Boolean correctlyDone;
    
    public ProblemSolving(SubSession subSession, Date time,
            EducationalResource educationalResource, Boolean correctlyDone,
            Integer timeSpent) {
        super(subSession, time, educationalResource,
                timeSpent);
        this.correctlyDone = correctlyDone;
    }

    public Boolean getCorrectlyDone() {
        return correctlyDone;
    }
    
}
