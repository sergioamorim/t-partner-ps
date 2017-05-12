/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.services.facade;

import br.com.tpartner.persistence.model.AccessSession;
import br.com.tpartner.persistence.model.Student;
import java.util.List;

/**
 *
 * @author sergio
 */
public interface AccessSessionFacade {
    /* Client*/
    public AccessSession save(AccessSession accessSession);
    public AccessSession update(AccessSession accessSession);
    public void delete(AccessSession accessSession);
    /* Aux */
    public AccessSession findById(int accessSessionId);
    public List<AccessSession> findByStudent(Student student);
    public List<AccessSession> findAll();
}
