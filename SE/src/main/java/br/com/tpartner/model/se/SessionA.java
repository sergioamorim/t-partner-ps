/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.model.se;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author sergio
 */

@Entity
@Table (name = "tp_se_session")

public class SessionA implements Serializable {
    @Id
    private Long sessionId;
    @OneToMany
    private List<Action> actions;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date timeStart;

    public SessionA() {
        
    }
    
    public SessionA(Date timeStart) {
        SessionA sessionA = new SessionA();
        sessionA.setTimeStart(timeStart);
    }
    
    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
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
