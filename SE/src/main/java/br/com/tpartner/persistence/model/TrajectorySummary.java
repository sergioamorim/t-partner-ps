/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.model;

import br.com.tpartner.persistence.crud.AccessSessionCRUD;
import br.com.tpartner.persistence.crud.StudentActionCRUD;
import br.com.tpartner.persistence.crud.SubSessionCRUD;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author sergio
 */

public class TrajectorySummary implements Serializable {
    private Student student;
    private Integer hitsTotal;
    private Integer failsTotal;
    private Integer problemsTriedTotal;
    private Integer contentsViewedTotal;
    private Integer problemSolvingTotalTime;
    private Integer contentViewTotalTime;
    private Integer newLevelsReached;
    private Integer dummyTestQuestionsDone;
    private Integer learningGoalsReachedTotal;
    private Integer subSessionsTotal;
    private Integer studentActionsTotal;
    private Integer contentsRepeated;
    private Integer problemsRepeated;
    private Double triesToHitAverage;
    private Double viewsPerContent;
    private Double triesPerProblem;
    private Double problemSolvingAverageTime;
    private Double contentViewAverageTime;
    private Double actionsPerSubSessionAverage;
    private String lastLevelReached;
    private String subSessionsTrackedJson;
    private String studentActionsTrackedJson;
    private String problemsTriedTrackedJson;
    private String contentsViewedTrackedJson;
    private String learningGoalsReachedTrackedJson;
    private Date timeStart;
    private Date timeEnd;

    public TrajectorySummary (Student student, Date timeStart, Date timeEnd) throws IOException {
        this.student = student;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.hitsTotal = 0;
        this.failsTotal = 0;
        this.problemSolvingTotalTime = 0;
        this.contentViewTotalTime = 0;
        this.contentsRepeated = 0;
        this.problemsRepeated = 0;
        this.newLevelsReached = 0;
        this.dummyTestQuestionsDone = 0;
        this.run();
    }

    private void run() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        AccessSessionCRUD accessSessionDAO = context.getBean(AccessSessionCRUD.class);
        List<AccessSession> accessSessions = accessSessionDAO.findByStudent(this.student);
        SubSessionCRUD subSessionDAO = context.getBean(SubSessionCRUD.class);
        StudentActionCRUD studentActionDAO = context.getBean(StudentActionCRUD.class);
        
        List<SubSession> subSessions = new ArrayList<SubSession>();
        List<SubSession> subSessionsTracked = new ArrayList<SubSession>();
        List<StudentAction> studentActionsTracked = new ArrayList<StudentAction>();
        List<String> problemsTriedTracked = new ArrayList<String>();
        List<String> contentsViewedTracked = new ArrayList<String>();
        List<String> learningGoalsReachedTracked = new ArrayList<String>();
        for (AccessSession accessSession : accessSessions) {
            if (accessSession.getTimeStart().before(this.timeEnd)) {
                subSessions.addAll(subSessionDAO.findByAccessSession(accessSession));
            }
        }
        for (SubSession subSession : subSessions) {
            if(subSession.getTimeStart().after(this.timeStart) && subSession.getTimeStart().before(this.timeEnd)) {
                subSessionsTracked.add(subSession);
                for (StudentAction studentAction : studentActionDAO.findBySubSession(subSession)) {
                    studentActionsTracked.add(studentAction);
                    if (studentAction.getProblemCorrectlyDone().equals("1") || studentAction.getProblemCorrectlyDone().equals("0")) {
                        if(studentAction.getProblemCorrectlyDone().equals("1")) {
                            this.hitsTotal++;
                        }
                        else {
                            this.failsTotal++;
                        }
                        if (!problemsTriedTracked.contains(studentAction.getProblemId())) {
                            problemsTriedTracked.add(studentAction.getProblemId());
                        }
                        else {
                            this.problemsRepeated++;
                        }
                        this.problemSolvingTotalTime += Integer.parseInt(studentAction.getProblemResponseTime());
                    }
                    else if (!studentAction.getContentId().equals("*")) {
                        if (!contentsViewedTracked.contains(studentAction.getContentId())) {
                            contentsViewedTracked.add(studentAction.getContentId());
                        }
                        else {
                            this.contentsRepeated++;
                        }
                        this.contentViewTotalTime += Integer.parseInt(studentAction.getContentViewTime());
                    }
                    else if (!studentAction.getGamificationLevel().equals("*")) {
                        this.newLevelsReached++;
                        this.lastLevelReached = studentAction.getGamificationLevel();
                    }
                    else if (studentAction.getType().equals("PROBLEM_BASED_EVALUATION")){
                        this.dummyTestQuestionsDone++;
                    }
                    else if (studentAction.getType().equals("LEARNING_GOAL")) {
                        learningGoalsReachedTracked.add(studentAction.getlGoalCurriculum());
                    }
                }
            }
        }
        this.problemsTriedTotal = this.failsTotal + this.hitsTotal;
        this.contentsViewedTotal = contentsViewedTracked.size();
        this.triesToHitAverage = (double) (this.hitsTotal+this.failsTotal) / this.problemsTriedTotal;
        this.contentViewAverageTime = (double) this.contentViewTotalTime / this.contentsViewedTotal;
        this.problemSolvingAverageTime = (double) this.problemSolvingTotalTime / this.problemsTriedTotal;
        this.viewsPerContent = (double) (this.contentsRepeated + this.contentsViewedTotal) / this.contentsViewedTotal;
        this.triesPerProblem = (double) (this.problemsRepeated + this.problemsTriedTotal) / this.problemsTriedTotal;
        this.learningGoalsReachedTotal = learningGoalsReachedTracked.size();
        this.studentActionsTotal = studentActionsTracked.size();
        this.subSessionsTotal = subSessionsTracked.size();
        this.actionsPerSubSessionAverage = (double) this.studentActionsTotal / this.subSessionsTotal;
        this.subSessionsTrackedJson = new Gson().toJson(subSessionsTracked);
        this.studentActionsTrackedJson = new Gson().toJson(studentActionsTracked);
        this.problemsTriedTrackedJson = new Gson().toJson(problemsTriedTracked);
        this.contentsViewedTrackedJson = new Gson().toJson(contentsViewedTracked);
        this.learningGoalsReachedTrackedJson = new Gson().toJson(learningGoalsReachedTracked);
        context.close();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getHitsTotal() {
        return hitsTotal;
    }

    public void setHitsTotal(Integer hitsTotal) {
        this.hitsTotal = hitsTotal;
    }

    public Integer getFailsTotal() {
        return failsTotal;
    }

    public void setFailsTotal(Integer failsTotal) {
        this.failsTotal = failsTotal;
    }

    public Integer getProblemsTriedTotal() {
        return problemsTriedTotal;
    }

    public void setProblemsTriedTotal(Integer problemsTriedTotal) {
        this.problemsTriedTotal = problemsTriedTotal;
    }

    public Integer getContentsViewedTotal() {
        return contentsViewedTotal;
    }

    public void setContentsViewedTotal(Integer contentsViewedTotal) {
        this.contentsViewedTotal = contentsViewedTotal;
    }

    public Integer getProblemSolvingTotalTime() {
        return problemSolvingTotalTime;
    }

    public void setProblemSolvingTotalTime(Integer problemSolvingTotalTime) {
        this.problemSolvingTotalTime = problemSolvingTotalTime;
    }

    public Integer getContentViewTotalTime() {
        return contentViewTotalTime;
    }

    public void setContentViewTotalTime(Integer contentViewTotalTime) {
        this.contentViewTotalTime = contentViewTotalTime;
    }

    public Integer getNewLevelsReached() {
        return newLevelsReached;
    }

    public void setNewLevelsReached(Integer newLevelsReached) {
        this.newLevelsReached = newLevelsReached;
    }

    public Integer getDummyTestQuestionsDone() {
        return dummyTestQuestionsDone;
    }

    public void setDummyTestQuestionsDone(Integer dummyTestQuestionsDone) {
        this.dummyTestQuestionsDone = dummyTestQuestionsDone;
    }

    public Integer getLearningGoalsReachedTotal() {
        return learningGoalsReachedTotal;
    }

    public void setLearningGoalsReachedTotal(Integer learningGoalsReachedTotal) {
        this.learningGoalsReachedTotal = learningGoalsReachedTotal;
    }

    public Integer getSubSessionsTotal() {
        return subSessionsTotal;
    }

    public void setSubSessionsTotal(Integer subSessionsTotal) {
        this.subSessionsTotal = subSessionsTotal;
    }

    public Integer getStudentActionsTotal() {
        return studentActionsTotal;
    }

    public void setStudentActionsTotal(Integer studentActionsTotal) {
        this.studentActionsTotal = studentActionsTotal;
    }

    public Integer getContentsRepeated() {
        return contentsRepeated;
    }

    public void setContentsRepeated(Integer contentsRepeated) {
        this.contentsRepeated = contentsRepeated;
    }

    public Integer getProblemsRepeated() {
        return problemsRepeated;
    }

    public void setProblemsRepeated(Integer problemsRepeated) {
        this.problemsRepeated = problemsRepeated;
    }

    public Double getTriesToHitAverage() {
        return triesToHitAverage;
    }

    public void setTriesToHitAverage(Double triesToHitAverage) {
        this.triesToHitAverage = triesToHitAverage;
    }

    public Double getViewsPerContent() {
        return viewsPerContent;
    }

    public void setViewsPerContent(Double viewsPerContent) {
        this.viewsPerContent = viewsPerContent;
    }

    public Double getTriesPerProblem() {
        return triesPerProblem;
    }

    public void setTriesPerProblem(Double triesPerProblem) {
        this.triesPerProblem = triesPerProblem;
    }

    public Double getProblemSolvingAverageTime() {
        return problemSolvingAverageTime;
    }

    public void setProblemSolvingAverageTime(Double problemSolvingAverageTime) {
        this.problemSolvingAverageTime = problemSolvingAverageTime;
    }

    public Double getContentViewAverageTime() {
        return contentViewAverageTime;
    }

    public void setContentViewAverageTime(Double contentViewAverageTime) {
        this.contentViewAverageTime = contentViewAverageTime;
    }

    public String getLastLevelReached() {
        return lastLevelReached;
    }

    public void setLastLevelReached(String lastLevelReached) {
        this.lastLevelReached = lastLevelReached;
    }

    public String getSubSessionsTrackedJson() {
        return subSessionsTrackedJson;
    }

    public void setSubSessionsTrackedJson(String subSessionsTrackedJson) {
        this.subSessionsTrackedJson = subSessionsTrackedJson;
    }

    public String getStudentActionsTrackedJson() {
        return studentActionsTrackedJson;
    }

    public void setStudentActionsTrackedJson(String studentActionsTrackedJson) {
        this.studentActionsTrackedJson = studentActionsTrackedJson;
    }

    public String getProblemsTriedTrackedJson() {
        return problemsTriedTrackedJson;
    }

    public void setProblemsTriedTrackedJson(String problemsTriedTrackedJson) {
        this.problemsTriedTrackedJson = problemsTriedTrackedJson;
    }

    public String getContentsViewedTrackedJson() {
        return contentsViewedTrackedJson;
    }

    public void setContentsViewedTrackedJson(String contentsViewedTrackedJson) {
        this.contentsViewedTrackedJson = contentsViewedTrackedJson;
    }

    public String getLearningGoalsReachedTrackedJson() {
        return learningGoalsReachedTrackedJson;
    }

    public void setLearningGoalsReachedTrackedJson(String learningGoalsReachedTrackedJson) {
        this.learningGoalsReachedTrackedJson = learningGoalsReachedTrackedJson;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Double getActionsPerSubSessionAverage() {
        return actionsPerSubSessionAverage;
    }

    public void setActionsPerSubSessionAverage(Double actionsPerSubSessionAverage) {
        this.actionsPerSubSessionAverage = actionsPerSubSessionAverage;
    }
    
}
