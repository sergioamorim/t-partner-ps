/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.repository.classes.se;

import br.com.tpartner.model.se.SessionA;
import br.com.tpartner.model.se.Student;
import br.com.tpartner.repository.classes.GenericHibernateRepository;
import br.com.tpartner.repository.interfaces.se.SessionARepositoryInterface;
import java.util.List;

/**
 *
 * @author sergio
 */
public class SessionARepository extends GenericHibernateRepository<SessionA, Long> implements SessionARepositoryInterface{

    @Override
    public List<SessionA> findSessionsAByStudent(Student student) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
