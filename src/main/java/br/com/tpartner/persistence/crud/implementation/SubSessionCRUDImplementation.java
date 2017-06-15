/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.crud.implementation;

import br.com.tpartner.persistence.crud.SubSessionCRUD;
import br.com.tpartner.persistence.model.AccessSession;
import br.com.tpartner.persistence.model.SubSession;
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
public class SubSessionCRUDImplementation 
        extends GenericCRUDImplementation<SubSession, Integer>
        implements SubSessionCRUD {
    
    @Override
    public List<SubSession> findByAccessSession(AccessSession accessSession) {
        Session session = getCurrentSession();
        Criteria createCriteria = session.createCriteria(SubSession.class);
        createCriteria.add(Restrictions.eq("accessSession", accessSession));
        List<SubSession> subSessions = (List<SubSession>) createCriteria.list();
        session.close();
        return subSessions;
    }

}
