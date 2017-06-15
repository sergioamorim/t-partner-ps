/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.crud.implementation;

import br.com.tpartner.persistence.crud.NonMappedStudentActionCRUD;
import br.com.tpartner.persistence.model.NonMappedStudentAction;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sergio
 */
@Repository
public class NonMappedStudentActionCRUDImplementation 
        extends GenericCRUDImplementation<NonMappedStudentAction, Integer>
        implements NonMappedStudentActionCRUD {
    
}

