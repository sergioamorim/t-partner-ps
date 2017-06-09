/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.crud.implementation;

import br.com.tpartner.persistence.model.StudentAction;
import br.com.tpartner.persistence.model.SubSession;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import br.com.tpartner.persistence.crud.StudentActionCRUD;
import br.com.tpartner.persistence.model.GamingTheSystem;

/**
 *
 * @author sergio
 */
@Repository
public class StudentActionCRUDImplementation implements StudentActionCRUD {
    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public Session getCurrentSession() {
        return sessionFactory.openSession();
    }
    
    @Override
    public StudentAction save(StudentAction action) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.save(action);
        tx.commit();
        session.close();
        return action;
    }
    
    @Override
    public StudentAction update(StudentAction action) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.update(action);
        tx.commit();
        session.close();
        return action;
    }
    
    @Override
    public void delete(StudentAction action) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.delete(action);
        tx.commit();
        session.close();
    }

    @Override
    public StudentAction findById(Integer actionId) {
        Session session = getCurrentSession();
        Criteria createCriteria = session.createCriteria(StudentAction.class);
        createCriteria.add(Restrictions.eq("id", actionId));
        StudentAction action = (StudentAction) createCriteria.uniqueResult();
        session.close();
        return action;
    }
    
    @Override
    public List<StudentAction> findBySubSession(SubSession subSession) {
        Session session = getCurrentSession();
        Criteria createCriteria = session.createCriteria(StudentAction.class);
        createCriteria.add(Restrictions.eq("subSession", subSession));
        List<StudentAction> actions = createCriteria.list();
        session.close();
        return actions;
    }
    
    @Override
    public List<StudentAction> findAll() {
        Session session = getCurrentSession();
        List<StudentAction> actions = session.createCriteria(StudentAction.class).list();
        session.close();
        return actions;
    }
    
    @Override
    public GamingTheSystem getGamingTheSystem() {
        return new GamingTheSystem();
    }
}
