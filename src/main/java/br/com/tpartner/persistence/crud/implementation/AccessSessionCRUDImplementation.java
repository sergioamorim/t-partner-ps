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
public class AccessSessionCRUDImplementation implements AccessSessionCRUD {
    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public Session getCurrentSession() {
        return sessionFactory.openSession();
    }
    
    @Override
    public AccessSession save(AccessSession accessSession) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.save(accessSession);
        tx.commit();
        session.close();
        return accessSession;
    }
    
    @Override
    public AccessSession update(AccessSession accessSession) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.update(accessSession);
        tx.commit();
        session.close();
        return accessSession;
    }
    
    @Override
    public void delete(AccessSession accessSession) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.delete(accessSession);
        tx.commit();
        session.close();
    }

    @Override
    public AccessSession findById(Integer accessSessionId) {
        Session session = getCurrentSession();
        Criteria createCriteria = session.createCriteria(AccessSession.class);
        createCriteria.add(Restrictions.eq("id", accessSessionId));
        AccessSession accessSession = (AccessSession) createCriteria.uniqueResult();
        session.close();
        return accessSession;
    }
    
    @Override
    public List<AccessSession> findByStudent(Student student) {
        Session session = getCurrentSession();
        Criteria createCriteria = session.createCriteria(AccessSession.class);
        createCriteria.add(Restrictions.eq("student", student));
        List<AccessSession> accessSessions = (List<AccessSession>) createCriteria.list();
        session.close();
        return accessSessions;
    }

    @Override
    public List<AccessSession> findAll() {
        Session session = getCurrentSession();
        List<AccessSession> accessSessions = session.createCriteria(AccessSession.class).list();
        session.close();
        return accessSessions;
    }
}
