/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.crud;

import br.com.tpartner.persistence.model.GamingTheSystem;
import br.com.tpartner.persistence.model.StudentAction;
import br.com.tpartner.persistence.model.SubSession;
import java.util.List;

/**
 *
 * @author sergio
 */
public interface StudentActionCRUD {
    /* Client */
    public StudentAction save(StudentAction action);
    public StudentAction update(StudentAction action);
    public void delete(StudentAction action);
    /* Aux */
    public StudentAction findById(Integer actionId);
    public List<StudentAction> findBySubSession(SubSession subSession);
    public List<StudentAction> findAll();
    
    public GamingTheSystem getGamingTheSystem();
}
