/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.services.facade;

import br.com.tpartner.persistence.model.EducationalResourceStats;
import java.util.List;

/**
 *
 * @author sergio
 */
public interface EducationalResourceStatsFacade {
    public EducationalResourceStats save(EducationalResourceStats educationalResourceStats);
    public EducationalResourceStats update(EducationalResourceStats educationalResourceStats);
    public void delete(EducationalResourceStats educationalResourceStats);
    public EducationalResourceStats findById(Integer educationalResourceStatsId);
    public List<EducationalResourceStats> findAll();
}
