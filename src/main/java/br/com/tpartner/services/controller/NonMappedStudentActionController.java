/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.services.controller;

import br.com.tpartner.persistence.crud.NonMappedStudentActionCRUD;
import br.com.tpartner.persistence.model.NonMappedStudentAction;
import br.com.tpartner.services.facade.NonMappedStudentActionFacade;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author sergio
 */
public class NonMappedStudentActionController implements NonMappedStudentActionFacade {
    @Autowired
    NonMappedStudentActionCRUD nonMappedStudentActionCRUD;
    
    public NonMappedStudentAction save(NonMappedStudentAction nonMappedStudentAction) {
        return this.nonMappedStudentActionCRUD.save(nonMappedStudentAction);
    }

    public NonMappedStudentAction update(NonMappedStudentAction nonMappedStudentAction) {
        return this.nonMappedStudentActionCRUD.update(nonMappedStudentAction);
    }

    public void delete(NonMappedStudentAction nonMappedStudentAction) {
        this.nonMappedStudentActionCRUD.delete(nonMappedStudentAction);
    }

    public NonMappedStudentAction findById(Integer nonMappedStudentActionId) {
        return this.nonMappedStudentActionCRUD.findById(nonMappedStudentActionId);
    }

    public List<NonMappedStudentAction> findAll() {
        return this.nonMappedStudentActionCRUD.findAll();
    }
}
