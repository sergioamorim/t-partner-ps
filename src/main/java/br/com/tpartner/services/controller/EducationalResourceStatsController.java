/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.services.controller;

import br.com.tpartner.persistence.crud.EducationalResourceStatsCRUD;
import br.com.tpartner.persistence.model.EducationalResourceStats;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author sergio
 */
public class EducationalResourceStatsController {
    @Autowired
    EducationalResourceStatsCRUD educationalResourceStatsCRUD;
    
    public EducationalResourceStats save(
            EducationalResourceStats educationalResourceStats) {
        
        return educationalResourceStatsCRUD.save(educationalResourceStats);
    }

    public EducationalResourceStats update(
            EducationalResourceStats educationalResourceStats) {
        
        return educationalResourceStatsCRUD.
                update(educationalResourceStats);
    }

    public void delete(EducationalResourceStats educationalResourceStats) {
        educationalResourceStatsCRUD.delete(educationalResourceStats);
    }

    public EducationalResourceStats findById(
            Integer educationalResourceStatsId) {
        
        return educationalResourceStatsCRUD.findById(
                educationalResourceStatsId);
    }

    public List<EducationalResourceStats> findAll() {
        return educationalResourceStatsCRUD.findAll();
    }
}
