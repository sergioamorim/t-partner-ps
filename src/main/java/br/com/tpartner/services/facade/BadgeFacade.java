/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.services.facade;

import br.com.tpartner.persistence.model.Badge;
import br.com.tpartner.persistence.model.Student;
import java.util.List;

/**
 *
 * @author sergio
 */
public interface BadgeFacade extends GenericFacade<Badge, Integer> {
    
    public List<Badge> findByStudent(Student student);
    public List<Badge> findByBadgeId(String badgeId);    
    
}
