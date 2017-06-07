/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @Column(name = "id")
    private final Long id;
    
    public Student(){
        this.id = new Long(0);
    }
    
    public Student(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
