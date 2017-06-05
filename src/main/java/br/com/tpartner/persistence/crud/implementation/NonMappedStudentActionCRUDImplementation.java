/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.crud.implementation;

import br.com.tpartner.persistence.crud.NonMappedStudentActionCRUD;
import br.com.tpartner.persistence.model.NonMappedStudentAction;
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
public class NonMappedStudentActionCRUDImplementation implements NonMappedStudentActionCRUD {
    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public Session getCurrentSession() {
        return sessionFactory.openSession();
    }
    
    @Override
    public NonMappedStudentAction save(NonMappedStudentAction nonMappedStudentAction) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.save(nonMappedStudentAction);
        tx.commit();
        session.close();
        return nonMappedStudentAction;
    }
    
    @Override
    public NonMappedStudentAction update(NonMappedStudentAction nonMappedStudentAction) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.update(nonMappedStudentAction);
        tx.commit();
        session.close();
        return nonMappedStudentAction;
    }
    
    @Override
    public void delete(NonMappedStudentAction nonMappedStudentAction) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.delete(nonMappedStudentAction);
        tx.commit();
        session.close();
    }

    @Override
    public NonMappedStudentAction findById(String nonMappedStudentActionId) {
        Session session = getCurrentSession();
        Criteria createCriteria = session.createCriteria(NonMappedStudentAction.class);
        createCriteria.add(Restrictions.eq("id", nonMappedStudentActionId));
        NonMappedStudentAction nonMappedStudentAction = (NonMappedStudentAction) createCriteria.uniqueResult();
        session.close();
        return nonMappedStudentAction;
    }

    @Override
    public List<NonMappedStudentAction> findAll() {
        Session session = getCurrentSession();
        List<NonMappedStudentAction> nonMappedStudentActions = session.createCriteria(NonMappedStudentAction.class).list();
        session.close();
        return nonMappedStudentActions;
    }
}

