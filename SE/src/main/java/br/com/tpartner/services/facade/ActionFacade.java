/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.services.facade;

import br.com.tpartner.persistence.model.Action;
import java.util.List;

/**
 *
 * @author sergio
 */
public interface ActionFacade {
    /* Client*/
    public Action save(Action action);
    public Action update(Action action);
    public void delete(Action action);
    /* Aux */
    public Action findById(int actionId);
    public List<Action> findAll();
}
