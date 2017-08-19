/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.services.controller;

import br.com.tpartner.persistence.crud.BadgeCRUD;
import br.com.tpartner.persistence.model.Badge;
import br.com.tpartner.persistence.model.Student;
import br.com.tpartner.services.facade.BadgeFacade;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author sergio
 */
public class BadgeController implements BadgeFacade {
    @Autowired
    BadgeCRUD badgeCRUD;
    
    public Badge save(Badge badge) {
        return this.badgeCRUD.save(badge);
    }

    public Badge update(Badge badge) {
        return this.badgeCRUD.update(badge);
    }

    public void delete(Badge badge) {
        this.badgeCRUD.delete(badge);
    }

    public Badge findById(Integer badgeId) {
        return this.badgeCRUD.findById(badgeId);
    }

    public List<Badge> findAll() {
        return this.badgeCRUD.findAll();
    }
    
    public List<Badge> findByStudent(Student student) {
        return this.badgeCRUD.findByStudent(student);
    }
    
    public List<Badge> findByBadgeId(String badgeId) {
        return this.badgeCRUD.findByBadgeId(badgeId);
    }
}
