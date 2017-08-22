/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.crud.implementation;

import br.com.tpartner.persistence.crud.BadgeCRUD;
import br.com.tpartner.persistence.model.Badge;
import br.com.tpartner.persistence.model.Student;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sergio
 */
@Repository
public class BadgeCRUDImplementation 
        extends GenericCRUDImplementation<Badge, Integer> implements BadgeCRUD {
    
    public List<Badge> findByStudent(Student student) {
        Session session = getCurrentSession();
        
        Criteria createCriteria;
        createCriteria = session.createCriteria(Badge.class);        
        List<Badge> badges = createCriteria.list();
        List<Badge> badgesReturn = new ArrayList<Badge>();
        for (Badge badge : badges) {
            if (badge.getSubSession().getAccessSession().getStudent().getId().equals(student.getId())) {
                badgesReturn.add(badge);
            }
        }
        session.close();
        return badgesReturn;
    }
    
    public List<Badge> findByBadgeId (String badgeId) {
        Session session = getCurrentSession();
        
        Criteria createCriteria;
        createCriteria = session.createCriteria(Badge.class);
        createCriteria.add(Restrictions.eq("badgeId", badgeId));
        
        List<Badge> badges = createCriteria.list();
        session.close();
        return badges;
    }
    
}
