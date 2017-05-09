/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author sergio
 */
@Entity
@Table(name = "action")
public class Action implements Serializable {
    @Id
    @SequenceGenerator(name = "action_id_seq", initialValue = 1,
            allocationSize = 1, sequenceName = "action_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "action_id_seq")
    private Integer id;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "date_time")
    private Date time;
    
    @Column(name = "type")
    private String type;
    
    @Column(name = "problem_id")
    private String problemId;
    
    @Column(name = "problem_correctly_done")
    private String problemCorrectlyDone;
    
    @Column(name = "problem_response_time")
    private String problemResponseTime;
    
    @Column(name = "content_id")
    private String contentId;
    
    @Column(name = "content_view_time")
    private String contentViewTime;
    
    @Column(name = "l_goal_curriculum")
    private String lGoalCurriculum;
    
    @Column(name = "l_goal_value")
    private String lGoalValue;
    
    @Column(name = "d_goal_domain")
    private String dGoalDomain;
    
    @Column(name = "d_goal_value")
    private String dGoalValue;
    
    @Column(name = "gamification_level")
    private String gamificationLevel;
    
    @Column(name = "number_of_points")
    private String numberOfPoints;
    
    @Column(name = "rs_type")
    private String rsType;
    
    @Column(name = "rs_completed")
    private String rsCompleted;
    
    @Column(name = "rs_correct")
    private String rsNCorrect;
    
    @Column(name = "rs_n_resources")
    private String rsNResources;
    
    @Column(name = "pbe_response_time")
    private String pbeResponseTime;
    
    @Column(name = "pbe_ability_score")
    private String pbeAbilityScore;
    
    @Column(name = "pbe_absolute_score")
    private String pbeAbsoluteScore;
    
    @Column(name = "pbe_num_correct")
    private String pbeNumCorrect;
    
    @Column(name = "pbe_num_blank")
    private String pbeNumBlank;
    
    @Column(name = "pbe_num_wrong")
    private String pbeNumWrong;
    
    @Column(name = "activity_loop_id")
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
    
    public Integer getActionId() {
        return id;
    }

    public void setActionId(Integer id) {
        this.id = id;
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
