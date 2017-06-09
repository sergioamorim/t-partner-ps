/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.model;

import br.com.tpartner.persistence.crud.EducationalResourceCRUD;
import br.com.tpartner.persistence.crud.ResourceInteractionCRUD;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 *
 * @author sergio
 */
public class GamingTheSystem implements Serializable {
    
    private final Date generatedTime;
    private final List<EducationalResourceStats> educationalResourcesStats;
    
    public GamingTheSystem() {
        
        AbstractApplicationContext context;
        context = new ClassPathXmlApplicationContext("spring.xml");
        
        EducationalResourceCRUD educationalResourceDAO;
        educationalResourceDAO = context.getBean(EducationalResourceCRUD.class);
        
        ResourceInteractionCRUD resourceInteractionDAO;
        resourceInteractionDAO = context.getBean(ResourceInteractionCRUD.class);
        
        List<EducationalResource> educationalResources;
        educationalResources = educationalResourceDAO.findAll();
        
        generatedTime = new Date();
        
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