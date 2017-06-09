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
import br.com.tpartner.persistence.model.GamingTheSystem;
import br.com.tpartner.services.facade.StudentActionFacade;

/**
 *
 * @author sergio
 */
public class StudentActionController implements StudentActionFacade {
    @Autowired
    StudentActionCRUD studentActionCRUD;
    
    public StudentAction save(StudentAction studentAction) {
        return this.studentActionCRUD.save(studentAction);
    }

    public StudentAction update(StudentAction studentAction) {
        return this.studentActionCRUD.update(studentAction);
    }

    public void delete(StudentAction studentAction) {
        this.studentActionCRUD.delete(studentAction);
    }

    public StudentAction findById(Integer studentActionId) {
        return this.studentActionCRUD.findById(studentActionId);
    }
    
    public List<StudentAction> findBySubSession(SubSession subSession) {
        return this.studentActionCRUD.findBySubSession(subSession);
    }

    public List<StudentAction> findAll() {
        return this.studentActionCRUD.findAll();
    }
    
    public GamingTheSystem getGamingTheSystem() {
        return this.studentActionCRUD.getGamingTheSystem();
    }
}
