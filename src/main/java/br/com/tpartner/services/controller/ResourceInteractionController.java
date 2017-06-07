/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.services.controller;

import br.com.tpartner.persistence.crud.ResourceInteractionCRUD;
import br.com.tpartner.persistence.model.ResourceInteraction;
import br.com.tpartner.services.facade.ResourceInteractionFacade;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author sergio
 */
public class ResourceInteractionController implements ResourceInteractionFacade {
    @Autowired
    ResourceInteractionCRUD resourceInteractionCRUD;
    
    public ResourceInteraction save(ResourceInteraction resourceInteraction) {
        return this.resourceInteractionCRUD.save(resourceInteraction);
    }

    public ResourceInteraction update(ResourceInteraction resourceInteraction) {
        return this.resourceInteractionCRUD.update(resourceInteraction);
    }

    public void delete(ResourceInteraction resourceInteraction) {
        this.resourceInteractionCRUD.delete(resourceInteraction);
    }

    public ResourceInteraction findById(Integer resourceInteractionId) {
        return this.resourceInteractionCRUD.findById(resourceInteractionId);
    }

    public List<ResourceInteraction> findAll() {
        return this.resourceInteractionCRUD.findAll();
    }
}
