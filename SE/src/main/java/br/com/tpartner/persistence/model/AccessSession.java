/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
    @SequenceGenerator(name = "access_session_id_seq", initialValue = 1,
            allocationSize = 1, sequenceName = "access_session_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "access_session_id_seq")
    private Integer id;
    
    @OneToMany
    @JoinColumn(name = "action_id", referencedColumnName = "id")
    private List<Action> actions;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "time_start")
    private Date timeStart;
    
    public AccessSession() {
        
    }
    
    public AccessSession(Date timeStart) {
        AccessSession accessSession = new AccessSession();
        accessSession.setTimeStart(timeStart);
    }
    
    public Integer getAccessSessionId() {
        return id;
    }

    public void setAccessSessionId(Integer id) {
        this.id = id;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }
}
