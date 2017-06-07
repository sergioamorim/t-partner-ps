/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.crud;

import br.com.tpartner.persistence.model.NonMappedStudentAction;
import java.util.List;

/**
 *
 * @author sergio
 */
public interface NonMappedStudentActionCRUD {
    /* Client */
    public NonMappedStudentAction save(NonMappedStudentAction nonMappedStudentAction);
    public NonMappedStudentAction update(NonMappedStudentAction nonMappedStudentAction);
    public void delete(NonMappedStudentAction nonMappedStudentAction);
    /* Aux */
    public NonMappedStudentAction findById(Integer nonMappedStudentActionId);
    public List<NonMappedStudentAction> findAll();
}
