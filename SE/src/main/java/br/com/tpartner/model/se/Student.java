/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.model.se;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author sergio
 */
@Entity
@Table (name = "tp_se_student")

public class Student implements Serializable {
    @Id
    private String studentId;
    @OneToMany
    private List<SessionA> sessionsA;
    
    public Student(){
    }
    
    public Student(String studentId){
        Student student = new Student();
        student.setStudentId(studentId);
    }
    
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public List<SessionA> getSessionsA() {
        return sessionsA;
    }

    public void setSessionsA(List<SessionA> sessionsA) {
        this.sessionsA = sessionsA;
    }
    
}
