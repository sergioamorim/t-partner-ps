/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.crud.implementation;

import br.com.tpartner.persistence.crud.ProblemSolvingCRUD;
import br.com.tpartner.persistence.model.ProblemSolving;
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
public class ProblemSolvingCRUDImplementation implements ProblemSolvingCRUD {
    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public Session getCurrentSession() {
        return sessionFactory.openSession();
    }
    
    @Override
    public ProblemSolving save(ProblemSolving problemSolving) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.save(problemSolving);
        tx.commit();
        session.close();
        return problemSolving;
    }
    
    @Override
    public ProblemSolving update(ProblemSolving problemSolving) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.update(problemSolving);
        tx.commit();
        session.close();
        return problemSolving;
    }
    
    @Override
    public void delete(ProblemSolving problemSolving) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.delete(problemSolving);
        tx.commit();
        session.close();
    }

    @Override
    public ProblemSolving findById(String problemSolvingId) {
        Session session = getCurrentSession();
        Criteria createCriteria = session.createCriteria(ProblemSolving.class);
        createCriteria.add(Restrictions.eq("id", problemSolvingId));
        ProblemSolving problemSolving = (ProblemSolving) createCriteria.uniqueResult();
        session.close();
        return problemSolving;
    }

    @Override
    public List<ProblemSolving> findAll() {
        Session session = getCurrentSession();
        List<ProblemSolving> problemSolvings = session.createCriteria(ProblemSolving.class).list();
        session.close();
        return problemSolvings;
    }
}