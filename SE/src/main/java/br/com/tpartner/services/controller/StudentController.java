/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.services.controller;

import br.com.tpartner.persistence.crud.StudentCRUD;
import br.com.tpartner.persistence.model.Student;
import br.com.tpartner.services.facade.StudentFacade;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author sergio
 */
public class StudentController implements StudentFacade {
    @Autowired
    StudentCRUD studentCRUD;
    
    public Student save(Student student) {
        return this.studentCRUD.save(student);
    }

    public Student update(Student student) {
        return this.studentCRUD.update(student);
    }

    public void delete(Student student) {
        this.studentCRUD.delete(student);
    }

    public Student findById(int studentId) {
        return this.studentCRUD.findById(studentId);
    }

    public List<Student> findAll() {
        return this.studentCRUD.findAll();
    }
}
