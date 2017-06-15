/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.crud.implementation;

import br.com.tpartner.persistence.crud.EducationalResourceCRUD;
import br.com.tpartner.persistence.model.EducationalResource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sergio
 */
@Repository
public class EducationalResourceCRUDImplementation 
        extends GenericCRUDImplementation<EducationalResource, String>
        implements EducationalResourceCRUD {
    
}