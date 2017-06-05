/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author sergio
 */
@Entity
public class ResourceInteraction extends StudentAction{
    
    @Column(name = "educational_resource")
    @ManyToOne
    private final EducationalResource educationalResource;
    
    @Column(name = "time_spent")
    private final Integer timeSpent;
    
    public ResourceInteraction(SubSession subSession, Date time, EducationalResource educationalResource, Integer timeSpent) {
        super(subSession, time);
        this.educationalResource = educationalResource;
        this.timeSpent = timeSpent;
    }

    public EducationalResource getEducationalResource() {
        return educationalResource;
    }

    public Integer getTimeSpent() {
        return timeSpent;
    }
    
}
