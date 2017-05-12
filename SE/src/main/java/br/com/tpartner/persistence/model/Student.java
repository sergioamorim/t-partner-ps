/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author sergio
 */
@Entity
@Table(name = "student")
public class Student implements Serializable {
    @Id
    @SequenceGenerator(name = "student_id_seq", initialValue = 1,
            allocationSize = 1, sequenceName = "student_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_seq")
    private Integer id;
    
    @Column(name = "student_id_string")
    private String studentIdString;
    
    public Student(){
    }
    
    public Student(String studentIdString){
        this.studentIdString = studentIdString;
    }
    
    public Integer getStudentId() {
        return id;
    }
    
    public void setStudentId(Integer id) {
        this.id = id;
    }
    
    public String getStudentIdString() {
        return studentIdString;
    }

    public void setStudentIdString(String studentIdString) {
        this.studentIdString = studentIdString;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
}
