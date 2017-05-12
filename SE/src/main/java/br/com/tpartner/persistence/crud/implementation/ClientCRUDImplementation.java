/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.crud.implementation;

import br.com.tpartner.persistence.crud.ClientCRUD;
import br.com.tpartner.persistence.model.Client;
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
public class ClientCRUDImplementation implements ClientCRUD {
    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public Client save(Client client) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.save(client);
        tx.commit();
        session.close();
        return client;
    }

    @Override
    public Client update(Client client) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.update(client);
        tx.commit();
        session.close();
        return client;
    }

    @Override
    public void delete(Client client) {
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.delete(client);
        tx.commit();
        session.close();
    }

    @Override
    public List<Client> findAll() {
        Session session = getCurrentSession();
        List<Client> listClient = session.createCriteria(Client.class).list();
        session.close();
        return listClient;
    }

    @Override
    public Client findById(int clientId) {
        Session session = getCurrentSession();
        Criteria createCriteria = session.createCriteria(Client.class);
        createCriteria.add(Restrictions.eq("id", clientId));
        Client c = (Client) createCriteria.uniqueResult();
        session.close();
        return c;
    }
}
