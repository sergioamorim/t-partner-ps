/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.se;

import java.util.List;

/**
 *
 * @author sergio
 */
public class Student {
    private String id;
    private List<Session> sessions;
    
    public Student(){
    }
    
    public Student(String id){
        Student student = new Student();
        student.setId(id);
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
    
}
