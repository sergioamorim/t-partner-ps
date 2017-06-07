/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.services.controller;

import br.com.tpartner.persistence.crud.EducationalResourceCRUD;
import br.com.tpartner.persistence.model.EducationalResource;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author sergio
 */
public class EducationalResourceController {
    @Autowired
    EducationalResourceCRUD educationalResourceCRUD;
    
    public EducationalResource save(EducationalResource educationalResource) {
        return this.educationalResourceCRUD.save(educationalResource);
    }

    public EducationalResource update(EducationalResource educationalResource) {
        return this.educationalResourceCRUD.update(educationalResource);
    }

    public void delete(EducationalResource educationalResource) {
        this.educationalResourceCRUD.delete(educationalResource);
    }

    public EducationalResource findById(String educationalResourceId) {
        return this.educationalResourceCRUD.findById(educationalResourceId);
    }

    public List<EducationalResource> findAll() {
        return this.educationalResourceCRUD.findAll();
    }
}
