/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.crud.implementation;

import br.com.tpartner.persistence.crud.EducationalResourceCRUD;
import br.com.tpartner.persistence.model.EducationalResource;
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
public class EducationalResourceCRUDImplementation implements EducationalResourceCRUD {
    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public Session getCurrentSession() {
        return sessionFactory.openSession();
    }
    
    @Override
    public EducationalResource save(EducationalResource educationalResource) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.save(educationalResource);
        tx.commit();
        session.close();
        return educationalResource;
    }
    
    @Override
    public EducationalResource update(EducationalResource educationalResource) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.update(educationalResource);
        tx.commit();
        session.close();
        return educationalResource;
    }
    
    @Override
    public void delete(EducationalResource educationalResource) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.delete(educationalResource);
        tx.commit();
        session.close();
    }

    @Override
    public EducationalResource findById(String educationalResourceId) {
        Session session = getCurrentSession();
        Criteria createCriteria = session.createCriteria(EducationalResource.class);
        createCriteria.add(Restrictions.eq("id", educationalResourceId));
        EducationalResource educationalResource = (EducationalResource) createCriteria.uniqueResult();
        session.close();
        return educationalResource;
    }

    @Override
    public List<EducationalResource> findAll() {
        Session session = getCurrentSession();
        List<EducationalResource> educationalResources = session.createCriteria(EducationalResource.class).list();
        session.close();
        return educationalResources;
    }
}
