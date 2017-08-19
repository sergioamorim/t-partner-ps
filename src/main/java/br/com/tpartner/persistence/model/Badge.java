/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author sergio
 */

@Entity
public class Badge extends StudentAction {
    
    @Column(name = "badge_id")
    private final String badgeId;
    
    public Badge(){
        this.badgeId = "none";
    }
    
    public Badge(SubSession subSession, Date time, String badgeId) {
        super(subSession, time);
        this.badgeId = badgeId;
    }

    public String getBadgeId() {
        return badgeId;
    }
    
}
