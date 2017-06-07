/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.services.facade;

import br.com.tpartner.persistence.model.EducationalResource;
import java.util.List;

/**
 *
 * @author sergio
 */
public interface EducationalResourceFacade {
    /* Client*/
    public EducationalResource save(EducationalResource educationalResource);
    public EducationalResource update(EducationalResource educationalResource);
    public void delete(EducationalResource educationalResource);
    /* Aux */
    public EducationalResource findById(String educationalResourceId);
    public List<EducationalResource> findAll();
}