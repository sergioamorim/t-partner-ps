/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author sergio
 */
@Entity
@Table(name = "non_mapped_student_action")
public class NonMappedStudentAction extends StudentAction {
    @Column(name = "type")
    private final String type;

    @Column(name = "l_goal_curriculum")
    private final String lGoalCurriculum;

    @Column(name = "l_goal_value")
    private final String lGoalValue;

    @Column(name = "d_goal_domain")
    private final String dGoalDomain;

    @Column(name = "d_goal_value")
    private final String dGoalValue;

    @Column(name = "gamification_level")
    private final String gamificationLevel;

    @Column(name = "number_of_points")
    private final String numberOfPoints;

    @Column(name = "rs_type")
    private final String rsType;

    @Column(name = "rs_completed")
    private final String rsCompleted;

    @Column(name = "rs_correct")
    private final String rsNCorrect;

    @Column(name = "rs_n_resources")
    private final String rsNResources;

    @Column(name = "pbe_response_time")
    private final String pbeResponseTime;

    @Column(name = "pbe_ability_score")
    private final String pbeAbilityScore;

    @Column(name = "pbe_absolute_score")
    private final String pbeAbsoluteScore;

    @Column(name = "pbe_num_correct")
    private final String pbeNumCorrect;

    @Column(name = "pbe_num_blank")
    private final String pbeNumBlank;

    @Column(name = "pbe_num_wrong")
    private final String pbeNumWrong;

    @Column(name = "activity_loop_id")
    private final String activityLoopId;

    public NonMappedStudentAction(Integer id, SubSession subSession, Date time,
            String type, String lGoalCurriculum, String lGoalValue,
            String dGoalDomain, String dGoalValue, String gamificationLevel,
            String numberOfPoints, String rsType, String rsCompleted,
            String rsNCorrect, String rsNResources, String pbeResponseTime,
            String pbeAbilityScore, String pbeAbsoluteScore,
            String pbeNumCorrect, String pbeNumBlank, String pbeNumWorng,
            String activityLoopId) {
        super(id, subSession, time);
        this.type = type;
        this.lGoalCurriculum = lGoalCurriculum;
        this.lGoalValue = lGoalValue;
        this.dGoalDomain = dGoalDomain;
        this.dGoalValue = dGoalValue;
        this.gamificationLevel = gamificationLevel;
        this.numberOfPoints = numberOfPoints;
        this.rsType = rsType;
        this.rsCompleted = rsCompleted;
        this.rsNCorrect = rsNCorrect;
        this.rsNResources = rsNResources;
        this.pbeResponseTime = pbeResponseTime;
        this.pbeAbilityScore = pbeAbilityScore;
        this.pbeAbsoluteScore = pbeAbsoluteScore;
        this.pbeNumCorrect = pbeNumCorrect;
        this.pbeNumBlank = pbeNumBlank;
        this.pbeNumWrong = pbeNumWorng;
        this.activityLoopId = activityLoopId;
    }

    public String getType() {
        return type;
    }

    public String getlGoalCurriculum() {
        return lGoalCurriculum;
    }

    public String getlGoalValue() {
        return lGoalValue;
    }

    public String getdGoalDomain() {
        return dGoalDomain;
    }

    public String getdGoalValue() {
        return dGoalValue;
    }

    public String getGamificationLevel() {
        return gamificationLevel;
    }

    public String getNumberOfPoints() {
        return numberOfPoints;
    }

    public String getRsType() {
        return rsType;
    }

    public String getRsCompleted() {
        return rsCompleted;
    }

    public String getRsNCorrect() {
        return rsNCorrect;
    }

    public String getRsNResources() {
        return rsNResources;
    }

    public String getPbeResponseTime() {
        return pbeResponseTime;
    }

    public String getPbeAbilityScore() {
        return pbeAbilityScore;
    }

    public String getPbeAbsoluteScore() {
        return pbeAbsoluteScore;
    }

    public String getPbeNumCorrect() {
        return pbeNumCorrect;
    }

    public String getPbeNumBlank() {
        return pbeNumBlank;
    }

    public String getPbeNumWrong() {
        return pbeNumWrong;
    }

    public String getActivityLoopId() {
        return activityLoopId;
    }
        
        
}
