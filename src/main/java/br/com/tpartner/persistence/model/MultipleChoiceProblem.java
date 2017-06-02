/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sergio
 */
public class MultipleChoiceProblem {
    private final String id;
    private final List<Integer> responseTimes;
    
    public MultipleChoiceProblem(String id, Integer responseTime) {
        this.id = id;
        this.responseTimes = new ArrayList<Integer>();
        this.responseTimes.add(responseTime);
    }
    
    public String getId() {
        return this.id;
    }

    public Integer getResponseTimeAverage() {
        Integer totalResponseTime;
        totalResponseTime = this.responseTimes.stream().mapToInt(Integer::intValue).sum();
        return totalResponseTime;
    }

    public void addResponseTime(Integer responseTime) {
        this.responseTimes.add(responseTime);
    }
    
}
