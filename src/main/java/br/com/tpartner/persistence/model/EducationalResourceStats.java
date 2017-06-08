/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.model;

import br.com.tpartner.persistence.crud.ResourceInteractionCRUD;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;import javax.persistence.SequenceGenerator;
;
import javax.persistence.Temporal;
import org.hibernate.type.DoubleType;
import org.hibernate.type.IntegerType;

/**
 *
 * @author sergio
 */
public class EducationalResourceStats implements Serializable {
    
    @Id
    @SequenceGenerator(name = "educational_resource_stats_id_seq",
            initialValue = 1, allocationSize = 1,
            sequenceName = "educational_resource_stats_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "educational_resource_stats_id_seq")
    private Integer id;
    
    @OneToOne
    private final EducationalResource educationalResource;
    
    private Double timeSpentAverage;
    private Integer timeSpentMedian;
    private final Integer maxTimeSpent;
    private final Integer minTimeSpent;
    private final Integer firstQuartile;
    private final Integer thirdQuartile;
    private final Integer interQuartileRange;
    private final Integer totalInteractions;
    
    @ManyToOne
    private final List<ResourceInteraction> resourceInteractions;
    
    @ManyToOne
    private final List<ResourceInteraction> outliersInteractions;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private final Date generatedTime;
    
    public EducationalResourceStats(){
        maxTimeSpent = IntegerType.ZERO;
        minTimeSpent = IntegerType.ZERO;
        firstQuartile = IntegerType.ZERO;
        thirdQuartile = IntegerType.ZERO;
        interQuartileRange = IntegerType.ZERO;
        totalInteractions = IntegerType.ZERO;
        timeSpentMedian = IntegerType.ZERO;
        timeSpentAverage = DoubleType.ZERO;
        resourceInteractions = new ArrayList();
        outliersInteractions = new ArrayList();
        generatedTime = new Date(IntegerType.ZERO);
        educationalResource = new EducationalResource();
    }
    
    public EducationalResourceStats(EducationalResource educationalResource, 
            ResourceInteractionCRUD resourceInteractionDAO) {
        
        generatedTime = new Date();
        
        this.educationalResource = educationalResource;
        
        resourceInteractions = resourceInteractionDAO.findByEducationalResource(
            educationalResource);
        
        totalInteractions = resourceInteractions.size();
        Collections.sort(resourceInteractions, new Comparator<ResourceInteraction>(){
            public int compare(ResourceInteraction o1, ResourceInteraction o2){
               return o1.getTimeSpent() - o2.getTimeSpent();
            }
        });
        
        firstQuartile = new Double(0.25 * totalInteractions).intValue() + 1;
        thirdQuartile = new Double(0.75 * totalInteractions).intValue() + 1;
        interQuartileRange = thirdQuartile - firstQuartile;
        
        if (totalInteractions % 2 == IntegerType.ZERO) {
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
        
        timeSpentAverage = DoubleType.ZERO;
        for (ResourceInteraction resourceInteraction : resourceInteractions) {
            timeSpentAverage += resourceInteraction.getTimeSpent();
        }
        timeSpentAverage /= totalInteractions;
        
        outliersInteractions = new ArrayList<ResourceInteraction>();
        for (int i = 0; i < (firstQuartile - (1.5 * interQuartileRange)); i++) {
            outliersInteractions.add(resourceInteractions.get(i));
        }
        for (int i = (totalInteractions - 1);
                i > (thirdQuartile + (1.5 * interQuartileRange)); i++) {
            outliersInteractions.add(resourceInteractions.get(i));
        }
        
    }

    public EducationalResource getEducationalResource() {
        return educationalResource;
    }
    
    public Double getTimeSpentAverage() {
        return timeSpentAverage;
    }

    public Integer getTimeSpentMedian() {
        return timeSpentMedian;
    }

    public Integer getMaxTimeSpent() {
        return maxTimeSpent;
    }

    public Integer getMinTimeSpent() {
        return minTimeSpent;
    }
    
    public List<ResourceInteraction> getResourceInteractions() {
        return resourceInteractions;
    }
    
    public Integer getFirstQuartile() {
        return firstQuartile;
    }
    
    public Integer getThirdQuartile() {
        return thirdQuartile;
    }

    public Integer getInterQuartileRange() {
        return interQuartileRange;
    }

    public List<ResourceInteraction> getOutliersInteractions() {
        return outliersInteractions;
    }

    public Integer getTotalInteractions() {
        return totalInteractions;
    }

    public Integer getId() {
        return id;
    }

    public Date getGeneratedTime() {
        return generatedTime;
    }
    
}
