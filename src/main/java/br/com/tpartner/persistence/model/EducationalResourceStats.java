/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.model;

import br.com.tpartner.persistence.crud.ResourceInteractionCRUD;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.hibernate.type.DoubleType;
import org.hibernate.type.IntegerType;

/**
 *
 * @author sergio
 */
public class EducationalResourceStats {
    private final EducationalResource educationalResource;
    private Double timeSpentAverage;
    private Integer timeSpentMedian;
    private Integer maxTimeSpent;
    private Integer minTimeSpent;
    
    public EducationalResourceStats(EducationalResource educationalResource, 
            ResourceInteractionCRUD resourceInteractionDAO) {
        
        this.educationalResource = educationalResource;
        
        List<ResourceInteraction> resourceInteractions;
        resourceInteractions = resourceInteractionDAO.findByEducationalResource(
            educationalResource);
        
        timeSpentAverage = DoubleType.ZERO;
        timeSpentMedian = IntegerType.ZERO;
        minTimeSpent = Integer.MAX_VALUE;
        maxTimeSpent = Integer.MIN_VALUE;
        
        Integer totalInteractions = resourceInteractions.size();
        Collections.sort(resourceInteractions, new Comparator<ResourceInteraction>(){
            public int compare(ResourceInteraction o1, ResourceInteraction o2){
               return o1.getTimeSpent() - o2.getTimeSpent();
            }
        });
        
        if (resourceInteractions.size() % 2 == IntegerType.ZERO) {
            timeSpentMedian = resourceInteractions.get(
                    totalInteractions/2).getTimeSpent();
            timeSpentMedian += resourceInteractions.get(
                    totalInteractions/2-1).getTimeSpent();
            timeSpentMedian /= 2;
        }
        else {
            timeSpentMedian = resourceInteractions.get(
                    totalInteractions/2).getTimeSpent();
        }
        
        minTimeSpent =resourceInteractions.get(IntegerType.ZERO).getTimeSpent();
        maxTimeSpent = resourceInteractions.get(totalInteractions-1).
                getTimeSpent();
        
        for (ResourceInteraction resourceInteraction : resourceInteractions) {
            timeSpentAverage += resourceInteraction.getTimeSpent();
        }
        timeSpentAverage /= totalInteractions;
    }

    public EducationalResource getEducationalResource() {
        return educationalResource;
    }
    
    public Double getTimeSpentAverage() {
        return timeSpentAverage;
    }

    public void setTimeSpentAverage(Double timeSpentAverage) {
        this.timeSpentAverage = timeSpentAverage;
    }

    public Integer getTimeSpentMedian() {
        return timeSpentMedian;
    }

    public void setTimeSpentMedian(Integer timeSpentMedian) {
        this.timeSpentMedian = timeSpentMedian;
    }

    public Integer getMaxTimeSpent() {
        return maxTimeSpent;
    }

    public void setMaxTimeSpent(Integer maxTimeSpent) {
        this.maxTimeSpent = maxTimeSpent;
    }

    public Integer getMinTimeSpent() {
        return minTimeSpent;
    }

    public void setMinTimeSpent(Integer minTimeSpent) {
        this.minTimeSpent = minTimeSpent;
    }
    
    
}
