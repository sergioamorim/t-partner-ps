/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.services.controller;

import br.com.tpartner.persistence.model.StudentAction;
import br.com.tpartner.persistence.model.SubSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.tpartner.persistence.crud.StudentActionCRUD;
import br.com.tpartner.services.facade.StudentActionFacade;

/**
 *
 * @author sergio
 */
public class StudentActionController implements StudentActionFacade {
    @Autowired
    StudentActionCRUD actionCRUD;
    
    public StudentAction save(StudentAction action) {
        return this.actionCRUD.save(action);
    }

    public StudentAction update(StudentAction action) {
        return this.actionCRUD.update(action);
    }

    public void delete(StudentAction action) {
        this.actionCRUD.delete(action);
    }

    public StudentAction findById(Integer actionId) {
        return this.actionCRUD.findById(actionId);
    }
    
    public List<StudentAction> findBySubSession(SubSession subSession) {
        return this.actionCRUD.findBySubSession(subSession);
    }

    public List<StudentAction> findAll() {
        return this.actionCRUD.findAll();
    }
    
}
