/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.model;

import br.com.tpartner.persistence.crud.EducationalResourceCRUD;
import br.com.tpartner.persistence.crud.ResourceInteractionCRUD;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 *
 * @author sergio
 */
public class GamingTheSystem {
    private List<EducationalResource> educationalResources;
    private List<EducationalResourceStats> educationalResourcesStats;
    
    private GamingTheSystem() {
        
        AbstractApplicationContext context;
        context = new ClassPathXmlApplicationContext("spring.xml");
        
        EducationalResourceCRUD educationalResourceDAO;
        educationalResourceDAO = context.getBean(EducationalResourceCRUD.class);
        
        ResourceInteractionCRUD resourceInteractionDAO;
        resourceInteractionDAO = context.getBean(ResourceInteractionCRUD.class);
        
        educationalResources = educationalResourceDAO.findAll();
        
        educationalResourcesStats = new ArrayList<EducationalResourceStats>();
        getStats(educationalResources, educationalResourcesStats,
                resourceInteractionDAO);
        
    }
 
    private void getStats(
            List<EducationalResource> educationalResources,
            List<EducationalResourceStats> educationalResourcesStats,
            ResourceInteractionCRUD resourceInteractionDAO) {
        if (!educationalResources.isEmpty()) {
            educationalResourcesStats.add(new EducationalResourceStats(
                    educationalResources.remove(0), resourceInteractionDAO));
            getStats(educationalResources, educationalResourcesStats,
                    resourceInteractionDAO);
        }
    }
}