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
@Table(name = "sub_session")
public class SubSession implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @JoinColumn(name = "access_session_id", referencedColumnName = "id")
    @ManyToOne
    private AccessSession accessSession;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "time_start")
    private Date timeStart;
    
    public SubSession() {
        
    }
    
    public SubSession(Date timeStart, AccessSession accessSession) {
        this.timeStart = timeStart;
        this.accessSession = accessSession;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AccessSession getAccessSession() {
        return accessSession;
    }

    public void setAccessSession(AccessSession accessSession) {
        this.accessSession = accessSession;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

}
