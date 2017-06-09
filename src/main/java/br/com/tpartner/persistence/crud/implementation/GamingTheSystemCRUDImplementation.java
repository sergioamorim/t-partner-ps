/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.crud.implementation;

import br.com.tpartner.persistence.crud.GamingTheSystemCRUD;
import br.com.tpartner.persistence.model.GamingTheSystem;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sergio
 */
@Repository
public class GamingTheSystemCRUDImplementation
        extends GenericCRUDImplementation<GamingTheSystem, Integer>
        implements GamingTheSystemCRUD {
    public GamingTheSystem newGamingTheSystem(){
        return new GamingTheSystem();
    }
}
