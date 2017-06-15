/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.crud;

import br.com.tpartner.persistence.model.AccessSession;
import br.com.tpartner.persistence.model.Student;
import java.util.List;

/**
 *
 * @author sergio
 */
public interface AccessSessionCRUD extends GenericCRUD<AccessSession, Integer> {
    
    public List<AccessSession> findByStudent(Student student);
    
}