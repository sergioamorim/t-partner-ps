/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.crud.implementation;

import br.com.tpartner.persistence.crud.ResourceInteractionCRUD;
import br.com.tpartner.persistence.model.ResourceInteraction;
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
public class ResourceInteractionCRUDImplementation implements ResourceInteractionCRUD {
    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public Session getCurrentSession() {
        return sessionFactory.openSession();
    }
    
    @Override
    public ResourceInteraction save(ResourceInteraction resourceInteraction) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.save(resourceInteraction);
        tx.commit();
        session.close();
        return resourceInteraction;
    }
    
    @Override
    public ResourceInteraction update(ResourceInteraction resourceInteraction) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.update(resourceInteraction);
        tx.commit();
        session.close();
        return resourceInteraction;
    }
    
    @Override
    public void delete(ResourceInteraction resourceInteraction) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.delete(resourceInteraction);
        tx.commit();
        session.close();
    }

    @Override
    public ResourceInteraction findById(Integer resourceInteractionId) {
        Session session = getCurrentSession();
        Criteria createCriteria = session.createCriteria(ResourceInteraction.class);
        createCriteria.add(Restrictions.eq("id", resourceInteractionId));
        ResourceInteraction resourceInteraction = (ResourceInteraction) createCriteria.uniqueResult();
        session.close();
        return resourceInteraction;
    }

    @Override
    public List<ResourceInteraction> findAll() {
        Session session = getCurrentSession();
        List<ResourceInteraction> resourceInteractions = session.createCriteria(ResourceInteraction.class).list();
        session.close();
        return resourceInteractions;
    }
}
