/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.crud.implementation;

import br.com.tpartner.persistence.crud.ResourceInteractionCRUD;
import br.com.tpartner.persistence.model.EducationalResource;
import br.com.tpartner.persistence.model.ResourceInteraction;
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
public class ResourceInteractionCRUDImplementation
        extends GenericCRUDImplementation<ResourceInteraction, Integer>
        implements ResourceInteractionCRUD {
    
    @Override
    public List<ResourceInteraction> findByEducationalResource(
            EducationalResource educationalResource) {
        
        Session session = getCurrentSession();
        
        Criteria createCriteria;
        createCriteria = session.createCriteria(ResourceInteraction.class);
        createCriteria.add(Restrictions.eq("educationalResource",
                                            educationalResource));
        
        List<ResourceInteraction> resourceInteractions = createCriteria.list();
        session.close();
        return resourceInteractions;
    }
    
}
