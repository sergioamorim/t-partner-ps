/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.crud;

import br.com.tpartner.persistence.model.AccessSession;
import java.util.List;

/**
 *
 * @author sergio
 */
public interface AccessSessionCRUD {
    /* Client */
    public AccessSession save(AccessSession accessSession);
    public AccessSession update(AccessSession accessSession);
    public void delete(AccessSession accessSession);
    /* Aux */
    public AccessSession findById(int accessSessionId);
    public List<AccessSession> findAll();
}