/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.crud.implementation;

import br.com.tpartner.persistence.crud.ActionCRUD;
import br.com.tpartner.persistence.model.AccessSession;
import br.com.tpartner.persistence.model.Action;
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
public class ActionCRUDImplementation implements ActionCRUD {
    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public Session getCurrentSession() {
        return sessionFactory.openSession();
    }
    
    @Override
    public Action save(Action action) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.save(action);
        tx.commit();
        session.close();
        return action;
    }
    
    @Override
    public Action update(Action action) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.update(action);
        tx.commit();
        session.close();
        return action;
    }
    
    @Override
    public void delete(Action action) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.delete(action);
        tx.commit();
        session.close();
    }

    @Override
    public Action findById(int actionId) {
        Session session = getCurrentSession();
        Criteria createCriteria = session.createCriteria(Action.class);
        createCriteria.add(Restrictions.eq("id", actionId));
        Action action = (Action) createCriteria.uniqueResult();
        session.close();
        return action;
    }
    
    @Override
    public List<Action> findByAccessSession(AccessSession accessSession) {
        Session session = getCurrentSession();
        Criteria createCriteria = session.createCriteria(Action.class);
        createCriteria.add(Restrictions.eq("accessSession", accessSession));
        List<Action> actions = createCriteria.list();
        session.close();
        return actions;
    }
    
    @Override
    public List<Action> findAll() {
        Session session = getCurrentSession();
        List<Action> actions = session.createCriteria(Action.class).list();
        session.close();
        return actions;
    }
}
