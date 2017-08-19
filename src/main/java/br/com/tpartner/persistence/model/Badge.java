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
public class Badge extends StudentAction {
    @ManyToOne
    private final Student student;
    
    @Column(name = "badge_id")
    private final String badgeId;
    
    public Badge(){
        this.student = new Student();
        this.badgeId = "none";
    }
    
    public Badge(SubSession subSession, Date time, Student student, String badgeId) {
        super(subSession, time);
        this.student = student;
        this.badgeId = badgeId;
    }

    public Student getStudent() {
        return student;
    }

    public String getBadgeId() {
        return badgeId;
    }
    
}
