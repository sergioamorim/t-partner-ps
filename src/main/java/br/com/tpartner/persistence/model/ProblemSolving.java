/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.model;

import java.util.Date;
import javax.persistence.Entity;

/**
 *
 * @author sergio
 */
@Entity
public class ProblemSolving extends ResourceInteraction {
    
    private final Boolean correctlyDone;
    
    public ProblemSolving(Integer id, SubSession subSession, Date time,
            EducationalResource educationalResource, Boolean correctlyDone,
            Integer timeSpent) {
        super(id, subSession, time, educationalResource,
                timeSpent);
        this.correctlyDone = correctlyDone;
    }

    public Boolean getCorrectlyDone() {
        return correctlyDone;
    }
    
}
