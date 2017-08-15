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
@Table(name = "access_session")
public class AccessSession implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @ManyToOne
    private Student student;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "time_start")
    private Date timeStart;
    
    public AccessSession() {
        
    }
    
    public AccessSession(Student student, Date timeStart) {
        this.student = student;
        this.timeStart = timeStart;
    }
    
    public Integer getAccessSessionId() {
        return id;
    }

    public void setAccessSessionId(Integer id) {
        this.id = id;
    }
    
    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    
}
