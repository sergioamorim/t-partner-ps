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
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sergio
 */
@Repository
public class SubSessionCRUDImplementation implements SubSessionCRUD {
    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public Session getCurrentSession() {
        return sessionFactory.openSession();
    }
    
    @Override
    public SubSession save(SubSession subSession) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.save(subSession);
        tx.commit();
        session.close();
        return subSession;
    }
    
    @Override
    public SubSession update(SubSession subSession) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.update(subSession);
        tx.commit();
        session.close();
        return subSession;
    }
    
    @Override
    public void delete(SubSession subSession) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.delete(subSession);
        tx.commit();
        session.close();
    }

    @Override
    public SubSession findById(Integer subSessionId) {
        Session session = getCurrentSession();
        Criteria createCriteria = session.createCriteria(SubSession.class);
        createCriteria.add(Restrictions.eq("id", subSessionId));
        SubSession subSession = (SubSession) createCriteria.uniqueResult();
        session.close();
        return subSession;
    }
    
    @Override
    public List<SubSession> findByAccessSession(AccessSession accessSession) {
        Session session = getCurrentSession();
        Criteria createCriteria = session.createCriteria(SubSession.class);
        createCriteria.add(Restrictions.eq("accessSession", accessSession));
        List<SubSession> subSessions = (List<SubSession>) createCriteria.list();
        session.close();
        return subSessions;
    }

    @Override
    public List<SubSession> findAll() {
        Session session = getCurrentSession();
        List<SubSession> subSessions = session.createCriteria(SubSession.class).list();
        session.close();
        return subSessions;
    }
}
