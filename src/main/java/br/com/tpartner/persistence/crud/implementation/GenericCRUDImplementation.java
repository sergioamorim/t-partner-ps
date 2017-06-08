/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.crud.implementation;

import br.com.tpartner.persistence.crud.GenericCRUD;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author sergio
 */
public abstract class GenericCRUDImplementation<T, ID> 
        implements GenericCRUD<T, ID> {

    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public Session getCurrentSession() {
        return sessionFactory.openSession();
    }
    
    private Class<T> persistentClass;

    @Override
    public T save(T entity) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
        return entity;
    }
    
    @Override
    public T update(T entity) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.update(entity);
        tx.commit();
        session.close();
        return entity;
    }
    
    @Override
    public void delete(T entity) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.delete(entity);
        tx.commit();
        session.close();
    }

    @Override
    public T findById(ID entityId) {
        Session session = getCurrentSession();
        Criteria createCriteria = session.createCriteria(persistentClass);
        createCriteria.add(Restrictions.eq("id", entityId));
        T entity = (T) createCriteria.uniqueResult();
        session.close();
        return entity;
    }

    @Override
    public List<T> findAll() {
        Session session = getCurrentSession();
        List<T> entitys = session.createCriteria(persistentClass).list();
        session.close();
        return entitys;
    }
    
}
