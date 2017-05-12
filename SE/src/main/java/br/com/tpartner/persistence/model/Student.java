/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    private Long id;
    
    @OneToMany(mappedBy="student", cascade = CascadeType.ALL)
    private List<AccessSession> accessSessions;
    
    public Student(){
    }
    
    public Student(Long id){
        this.id = id;
    }
    
    public Long getStudentId() {
        return id;
    }
    
    public void setStudentId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<AccessSession> getAccessSessions() {
        return accessSessions;
    }

    public void setAccessSessions(List<AccessSession> accessSessions) {
        this.accessSessions = accessSessions;
    }
    
}
