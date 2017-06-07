/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author sergio
 */
@Entity
@Table(name = "student_action")
public class StudentAction implements Serializable {
    @Id 
    @SequenceGenerator(name = "action_id_seq", initialValue = 1,
            allocationSize = 1, sequenceName = "action_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "action_id_seq")
    private Integer id;
    
    @JoinColumn(name = "sub_session_id", referencedColumnName = "id")
    @ManyToOne
    private final SubSession subSession;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "date_time")
    private final Date time;
    
    public StudentAction() {
        this.subSession = new SubSession();
        this.time = new Date();
    }
    
    public StudentAction(SubSession subSession, Date time) {
        this.subSession = subSession;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public SubSession getSubSession() {
        return subSession;
    }

    public Date getTime() {
        return time;
    }
}