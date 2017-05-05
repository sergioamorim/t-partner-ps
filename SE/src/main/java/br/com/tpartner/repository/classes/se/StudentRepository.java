/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.repository.classes.se;

import br.com.tpartner.model.se.Student;
import br.com.tpartner.repository.classes.GenericHibernateRepository;
import br.com.tpartner.repository.interfaces.se.StudentRepositoryInterface;

/**
 *
 * @author sergio
 */
public class StudentRepository extends GenericHibernateRepository<Student, String> implements StudentRepositoryInterface {
    
}
