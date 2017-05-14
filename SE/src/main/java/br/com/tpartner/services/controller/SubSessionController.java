/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.services.controller;

import br.com.tpartner.persistence.crud.SubSessionCRUD;
import br.com.tpartner.persistence.model.AccessSession;
import br.com.tpartner.persistence.model.SubSession;
import br.com.tpartner.services.facade.SubSessionFacade;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author sergio
 */
public class SubSessionController implements SubSessionFacade {
    @Autowired
    SubSessionCRUD subSessionCRUD;
    
    public SubSession save(SubSession subSession) {
        return this.subSessionCRUD.save(subSession);
    }

    public SubSession update(SubSession subSession) {
        return this.subSessionCRUD.update(subSession);
    }

    public void delete(SubSession subSession) {
        this.subSessionCRUD.delete(subSession);
    }

    public SubSession findById(Integer subSessionId) {
        return this.subSessionCRUD.findById(subSessionId);
    }
    
    public List<SubSession> findByAccessSession(AccessSession accessSession) {
        return this.subSessionCRUD.findByAccessSession(accessSession);
    }

    public List<SubSession> findAll() {
        return this.subSessionCRUD.findAll();
    }
}
