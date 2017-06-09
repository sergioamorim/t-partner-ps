/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.services.controller;

import br.com.tpartner.persistence.crud.GamingTheSystemCRUD;
import br.com.tpartner.persistence.model.GamingTheSystem;
import br.com.tpartner.services.facade.GamingTheSystemFacade;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author sergio
 */
public class GamingTheSystemController implements GamingTheSystemFacade {
    @Autowired
    GamingTheSystemCRUD gamingTheSystemCRUD;
    
    public GamingTheSystem save(GamingTheSystem gamingTheSystem) {
        return gamingTheSystemCRUD.save(gamingTheSystem);
    }

    public GamingTheSystem update(GamingTheSystem gamingTheSystem) {
        return gamingTheSystemCRUD.update(gamingTheSystem);
    }

    public void delete(GamingTheSystem gamingTheSystem) {
        gamingTheSystemCRUD.delete(gamingTheSystem);
    }

    public GamingTheSystem findById(Integer gamingTheSystemId) {
        return gamingTheSystemCRUD.findById(gamingTheSystemId);
    }

    public List<GamingTheSystem> findAll() {
        return gamingTheSystemCRUD.findAll();
    }
    
    public GamingTheSystem newGamingTheSystem() {
        return gamingTheSystemCRUD.newGamingTheSystem();
    }
}
