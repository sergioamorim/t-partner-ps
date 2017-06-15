/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.crud.implementation;

import br.com.tpartner.persistence.crud.ClientCRUD;
import br.com.tpartner.persistence.model.Client;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sergio
 */
@Repository
public class ClientCRUDImplementation 
        extends GenericCRUDImplementation<Client, Integer>
        implements ClientCRUD {
    
}
