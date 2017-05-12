/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.services.controller;

import br.com.tpartner.persistence.crud.ActionCRUD;
import br.com.tpartner.persistence.model.AccessSession;
import br.com.tpartner.persistence.model.Action;
import br.com.tpartner.services.facade.ActionFacade;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author sergio
 */
public class ActionController implements ActionFacade {
    @Autowired
    ActionCRUD actionCRUD;
    
    public Action save(Action action) {
        return this.actionCRUD.save(action);
    }

    public Action update(Action action) {
        return this.actionCRUD.update(action);
    }

    public void delete(Action action) {
        this.actionCRUD.delete(action);
    }

    public Action findById(int actionId) {
        return this.actionCRUD.findById(actionId);
    }
    
    public List<Action> findByAccessSession(AccessSession accessSession) {
        return this.actionCRUD.findByAccessSession(accessSession);
    }

    public List<Action> findAll() {
        return this.actionCRUD.findAll();
    }
    
}
