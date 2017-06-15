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
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import br.com.tpartner.persistence.crud.StudentActionCRUD;
import br.com.tpartner.persistence.model.GamingTheSystem;

/**
 *
 * @author sergio
 */
@Repository
public class StudentActionCRUDImplementation 
        extends GenericCRUDImplementation<StudentAction, Integer>
        implements StudentActionCRUD {
    
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
    public GamingTheSystem getGamingTheSystem() {
        return new GamingTheSystem();
    }
}
