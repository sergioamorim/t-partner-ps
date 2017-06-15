/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.crud.implementation;

import br.com.tpartner.persistence.crud.AccessSessionCRUD;
import br.com.tpartner.persistence.model.AccessSession;
import br.com.tpartner.persistence.model.Student;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sergio
 */
@Repository
public class AccessSessionCRUDImplementation
        extends GenericCRUDImplementation<AccessSession, Integer>
        implements AccessSessionCRUD {
    
    @Override
    public List<AccessSession> findByStudent(Student student) {
        Session session = getCurrentSession();
        Criteria createCriteria = session.createCriteria(AccessSession.class);
        createCriteria.add(Restrictions.eq("student", student));
        List<AccessSession> accessSessions = (List<AccessSession>) createCriteria.list();
        session.close();
        return accessSessions;
    }
    
}
