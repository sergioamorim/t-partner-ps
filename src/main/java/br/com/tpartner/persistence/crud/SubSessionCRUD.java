/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.crud;

import br.com.tpartner.persistence.model.AccessSession;
import br.com.tpartner.persistence.model.SubSession;
import java.util.List;

/**
 *
 * @author sergio
 */
public interface SubSessionCRUD {
    /* Client */
    public SubSession save(SubSession subSession);
    public SubSession update(SubSession subSession);
    public void delete(SubSession subSession);
    /* Aux */
    public SubSession findById(Integer subSessionId);
    public List<SubSession> findByAccessSession(AccessSession accessSession);
    public List<SubSession> findAll();
}
