/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author sergio
 */
public class TrajectorySummariesRequest {
    private List<Student> students;
    private Date startDate;
    private Date endDate;
    
    public List<Student> getStudents() {
        return this.students;
    }
    public Date getStartDate() {
        return this.startDate;
    }
    public Date getEndDate() {
        return this.endDate;
    }
}
