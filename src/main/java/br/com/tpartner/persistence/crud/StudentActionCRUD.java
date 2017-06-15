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
public interface StudentActionCRUD
        extends GenericCRUD<StudentAction, Integer> {
    
    public List<StudentAction> findBySubSession(SubSession subSession);    
    public GamingTheSystem getGamingTheSystem();
    
}
