/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.crud;

import br.com.tpartner.persistence.model.Client;
import java.util.List;

/**
 *
 * @author sergio
 */
public interface ClientCRUD {
    /* Client */
    public Client save(Client client);
    public Client update(Client client);
    public void delete(Client client);
    /* Aux */
    public Client findById(Integer clientId);
    public List<Client> findAll();
}
