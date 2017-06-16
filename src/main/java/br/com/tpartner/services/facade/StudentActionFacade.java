/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.services.facade;

import br.com.tpartner.persistence.model.GamingTheSystem;
import br.com.tpartner.persistence.model.SubSession;
import br.com.tpartner.persistence.model.StudentAction;
import java.util.List;

/**
 *
 * @author sergio
 */
public interface StudentActionFacade
        extends GenericFacade<StudentAction, Integer> {
    
    public List<StudentAction> findBySubSession(SubSession subSession);    
    public GamingTheSystem getGamingTheSystem();
    
}
