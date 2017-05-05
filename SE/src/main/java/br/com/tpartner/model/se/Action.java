/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.model.se;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;


/**
 *
 * @author sergio
 */

@Entity
@Table (name = "tp_se_action")

public class Action implements Serializable {
    @Id
    private Long actionId;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date time;
    private String type;
    private String problemId;
    private String problemCorrectlyDone;
    private String problemResponseTime;
    private String contentId;
    private String contentViewTime;
    private String lGoalCurriculum;
    private String lGoalValue;
    private String dGoalDomain;
    private String dGoalValue;
    private String gamificationLevel;
    private String numberOfPoints;
    private String rsType;
    private String rsCompleted;
    private String rsNCorrect;
    private String rsNResources;
    private String pbeResponseTime;
    private String pbeAbilityScore;
    private String pbeAbsoluteScore;
    private String pbeNumCorrect;
    private String pbeNumBlank;
    private String pbeNumWrong;
    private String activityLoopId;

    public Action() {
    }
    
    public Action(Date time, String type, String problemId, 
            String problemCorrectlyDone, String problemResponseTime,
            String contentId, String contentViewTime, String lGoalCurriculum,
            String lGoalValue, String dGoalDomain, String dGoalValue,
            String gamificationLevel, String numberOfPoints, String rsType,
            String rsCompleted, String rsNCorrect, String rsNResources,
            String pbeResponseTime, String pbeAbilityScore, 
            String pbeAbsoluteScore, String pbeNumCorrect, String pbeNumBlank,
            String pbeNumWrong, String activityLoopId){
        Action action = new Action();
        action.setTime(time);
        action.setType(type);
        action.setProblemId(problemId);
        action.setProblemCorrectlyDone(problemCorrectlyDone);
        action.setProblemResponseTime(problemResponseTime);
        action.setContentId(contentId);
        action.setContentViewTime(contentViewTime);
        action.setlGoalCurriculum(lGoalCurriculum);
        action.setlGoalValue(lGoalValue);
        action.setdGoalDomain(dGoalDomain);
        action.setdGoalValue(dGoalValue);
        action.setGamificationLevel(gamificationLevel);
        action.setNumberOfPoints(numberOfPoints);
        action.setRsType(rsType);
        action.setRsCompleted(rsCompleted);
        action.setRsNCorrect(rsNCorrect);
        action.setRsNResources(rsNResources);
        action.setPbeResponseTime(pbeResponseTime);
        action.setPbeAbilityScore(pbeAbilityScore);
        action.setPbeAbsoluteScore(pbeAbsoluteScore);
        action.setPbeNumCorrect(pbeNumCorrect);
        action.setPbeNumBlank(pbeNumBlank);
        action.setPbeNumWrong(pbeNumWrong);
        action.setActivityLoopId(activityLoopId);
    }
    
    public Long getActionId() {
        return actionId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
    
    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type = type;
    }
    
    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    public String getProblemCorrectlyDone() {
        return problemCorrectlyDone;
    }

    public void setProblemCorrectlyDone(String problemCorrectlyDone) {
        this.problemCorrectlyDone = problemCorrectlyDone;
    }

    public String getProblemResponseTime() {
        return problemResponseTime;
    }

    public void setProblemResponseTime(String problemResponseTime) {
        this.problemResponseTime = problemResponseTime;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getContentViewTime() {
        return contentViewTime;
    }

    public void setContentViewTime(String contentViewTime) {
        this.contentViewTime = contentViewTime;
    }

    public String getlGoalCurriculum() {
        return lGoalCurriculum;
    }

    public void setlGoalCurriculum(String lGoalCurriculum) {
        this.lGoalCurriculum = lGoalCurriculum;
    }

    public String getlGoalValue() {
        return lGoalValue;
    }

    public void setlGoalValue(String lGoalValue) {
        this.lGoalValue = lGoalValue;
    }

    public String getdGoalDomain() {
        return dGoalDomain;
    }

    public void setdGoalDomain(String dGoalDomain) {
        this.dGoalDomain = dGoalDomain;
    }

    public String getdGoalValue() {
        return dGoalValue;
    }

    public void setdGoalValue(String dGoalValue) {
        this.dGoalValue = dGoalValue;
    }

    public String getGamificationLevel() {
        return gamificationLevel;
    }

    public void setGamificationLevel(String gamificationLevel) {
        this.gamificationLevel = gamificationLevel;
    }

    public String getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(String numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    public String getRsType() {
        return rsType;
    }

    public void setRsType(String rsType) {
        this.rsType = rsType;
    }

    public String getRsCompleted() {
        return rsCompleted;
    }

    public void setRsCompleted(String rsCompleted) {
        this.rsCompleted = rsCompleted;
    }

    public String getRsNCorrect() {
        return rsNCorrect;
    }

    public void setRsNCorrect(String rsNCorrect) {
        this.rsNCorrect = rsNCorrect;
    }

    public String getRsNResources() {
        return rsNResources;
    }

    public void setRsNResources(String rsNResources) {
        this.rsNResources = rsNResources;
    }

    public String getPbeResponseTime() {
        return pbeResponseTime;
    }

    public void setPbeResponseTime(String pbeResponseTime) {
        this.pbeResponseTime = pbeResponseTime;
    }

    public String getPbeAbilityScore() {
        return pbeAbilityScore;
    }

    public void setPbeAbilityScore(String pbeAbilityScore) {
        this.pbeAbilityScore = pbeAbilityScore;
    }

    public String getPbeAbsoluteScore() {
        return pbeAbsoluteScore;
    }

    public void setPbeAbsoluteScore(String pbeAbsoluteScore) {
        this.pbeAbsoluteScore = pbeAbsoluteScore;
    }

    public String getPbeNumCorrect() {
        return pbeNumCorrect;
    }

    public void setPbeNumCorrect(String pbeNumCorrect) {
        this.pbeNumCorrect = pbeNumCorrect;
    }

    public String getPbeNumBlank() {
        return pbeNumBlank;
    }

    public void setPbeNumBlank(String pbeNumBlank) {
        this.pbeNumBlank = pbeNumBlank;
    }

    public String getPbeNumWrong() {
        return pbeNumWrong;
    }

    public void setPbeNumWrong(String pbeNumWrong) {
        this.pbeNumWrong = pbeNumWrong;
    }

    public String getActivityLoopId() {
        return activityLoopId;
    }

    public void setActivityLoopId(String activityLoopId) {
        this.activityLoopId = activityLoopId;
    }
    
}
