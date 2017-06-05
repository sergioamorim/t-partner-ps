/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.model;

import br.com.tpartner.persistence.crud.AccessSessionCRUD;
import br.com.tpartner.persistence.crud.StudentActionCRUD;
import br.com.tpartner.persistence.crud.SubSessionCRUD;
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
    private final Student student;
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
    private List<SubSession> subSessionsTracked;
    private List<StudentAction> studentActionsTracked;
    private List<EducationalResource> problemsTried;
    private List<EducationalResource> contentsViewed;
    private List<String> learningGoalsReachedTracked;
    private final Date timeStart;
    private final Date timeEnd;

    public TrajectorySummary (Student student, Date timeStart, Date timeEnd)
            throws IOException {
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
        
        AbstractApplicationContext context;
        context = new ClassPathXmlApplicationContext("spring.xml");
        
        AccessSessionCRUD accessSessionDAO;
        accessSessionDAO = context.getBean(AccessSessionCRUD.class);
        
        List<AccessSession> accessSessions;
        accessSessions = accessSessionDAO.findByStudent(this.student);
        
        SubSessionCRUD subSessionDAO = context.getBean(SubSessionCRUD.class);
        
        StudentActionCRUD studentActionDAO;
        studentActionDAO = context.getBean(StudentActionCRUD.class);
        
        List<SubSession> subSessions = new ArrayList<SubSession>();
        this.subSessionsTracked = new ArrayList<SubSession>();
        this.studentActionsTracked = new ArrayList<StudentAction>();
        this.problemsTried = new ArrayList<EducationalResource>();
        this.contentsViewed = new ArrayList<EducationalResource>();
        this.learningGoalsReachedTracked = new ArrayList<String>();
        for (AccessSession accessSession : accessSessions) {
            if (accessSession.getTimeStart().before(this.timeEnd)) {
                
                subSessions.addAll(subSessionDAO.findByAccessSession(
                        accessSession));
                
            }
        }
        for (SubSession subSession : subSessions) {
            if(subSession.getTimeStart().after(this.timeStart) &&
                    subSession.getTimeStart().before(this.timeEnd)) {
                subSessionsTracked.add(subSession);
                
                for (StudentAction studentAction : studentActionDAO.
                        findBySubSession(subSession)) {
                    
                    studentActionsTracked.add(studentAction);
                    if (studentAction.getClass() == ProblemSolving.class) {
                        ProblemSolving problemSolving;
                        problemSolving = (ProblemSolving) studentAction;
                        if(problemSolving.getCorrectlyDone()) {
                            this.hitsTotal++;
                        }
                        else {
                            this.failsTotal++;
                        }
                        
                        EducationalResource educationalResource =
                                problemSolving.getEducationalResource();
                        
                        if (!problemsTried.contains(educationalResource)) {
                            problemsTried.add(educationalResource);
                        }
                        else {
                            this.problemsRepeated++;
                        }
                        this.problemSolvingTotalTime +=
                                problemSolving.getTimeSpent();
                    }
                    else if (studentAction.getClass() ==
                            ResourceInteraction.class) {
                        
                        ResourceInteraction resourceInteraction;
                        resourceInteraction = 
                                (ResourceInteraction) studentAction;
                        
                        EducationalResource educationalResource =
                                resourceInteraction.getEducationalResource();
                        
                        if (!contentsViewed.contains(educationalResource)) {
                            contentsViewed.add(educationalResource);
                        }
                        else {
                            this.contentsRepeated++;
                        }
                        this.contentViewTotalTime +=
                                resourceInteraction.getTimeSpent();
                    }
                    else {
                        
                        NonMappedStudentAction nonMappedStudentAction;
                        nonMappedStudentAction = 
                                (NonMappedStudentAction) studentAction;
                        
                        if (!nonMappedStudentAction.getGamificationLevel().
                                equals("*")) {
                            
                            this.newLevelsReached++;
                            
                            this.lastLevelReached = 
                                nonMappedStudentAction.getGamificationLevel();
                            
                        }
                        else if (nonMappedStudentAction.getType().equals(
                                "PROBLEM_BASED_EVALUATION")){
                            this.dummyTestQuestionsDone++;
                        }
                        else if (nonMappedStudentAction.getType().equals(
                                "LEARNING_GOAL")) {
                            learningGoalsReachedTracked.add(
                                nonMappedStudentAction.getlGoalCurriculum());
                        }
                    }
                }
            }
        }
        this.problemsTriedTotal = this.failsTotal + this.hitsTotal;
        this.contentsViewedTotal = contentsViewed.size();
        
        this.triesToHitAverage = 
                (double) this.problemsTriedTotal / this.hitsTotal;
        
        this.contentViewAverageTime = 
                (double) this.contentViewTotalTime / this.contentsViewedTotal;
        
        this.problemSolvingAverageTime = 
                (double) this.problemSolvingTotalTime / this.problemsTriedTotal;
        
        this.viewsPerContent = 
                (double) (this.contentsRepeated + this.contentsViewedTotal) /
                    this.contentsViewedTotal;
        
        this.triesPerProblem =
                (double) (this.problemsRepeated + this.problemsTriedTotal) /
                    this.problemsTriedTotal;
        
        this.learningGoalsReachedTotal = learningGoalsReachedTracked.size();
        this.studentActionsTotal = studentActionsTracked.size();
        this.subSessionsTotal = subSessionsTracked.size();
        
        this.actionsPerSubSessionAverage =
                (double) this.studentActionsTotal / this.subSessionsTotal;
        
        context.close();
    }

    public Student getStudent() {
        return student;
    }

    public Integer getHitsTotal() {
        return hitsTotal;
    }

    public Integer getFailsTotal() {
        return failsTotal;
    }

    public Integer getProblemsTriedTotal() {
        return problemsTriedTotal;
    }

    public Integer getContentsViewedTotal() {
        return contentsViewedTotal;
    }

    public Integer getProblemSolvingTotalTime() {
        return problemSolvingTotalTime;
    }

    public Integer getContentViewTotalTime() {
        return contentViewTotalTime;
    }

    public Integer getNewLevelsReached() {
        return newLevelsReached;
    }

    public Integer getDummyTestQuestionsDone() {
        return dummyTestQuestionsDone;
    }

    public Integer getLearningGoalsReachedTotal() {
        return learningGoalsReachedTotal;
    }

    public Integer getSubSessionsTotal() {
        return subSessionsTotal;
    }

    public Integer getStudentActionsTotal() {
        return studentActionsTotal;
    }

    public Integer getContentsRepeated() {
        return contentsRepeated;
    }

    public Integer getProblemsRepeated() {
        return problemsRepeated;
    }

    public Double getTriesToHitAverage() {
        return triesToHitAverage;
    }

    public Double getViewsPerContent() {
        return viewsPerContent;
    }

    public Double getTriesPerProblem() {
        return triesPerProblem;
    }

    public Double getProblemSolvingAverageTime() {
        return problemSolvingAverageTime;
    }

    public Double getContentViewAverageTime() {
        return contentViewAverageTime;
    }

    public Double getActionsPerSubSessionAverage() {
        return actionsPerSubSessionAverage;
    }

    public String getLastLevelReached() {
        return lastLevelReached;
    }

    public List<SubSession> getSubSessionsTracked() {
        return subSessionsTracked;
    }

    public List<StudentAction> getStudentActionsTracked() {
        return studentActionsTracked;
    }

    public List<EducationalResource> getProblemsTried() {
        return problemsTried;
    }

    public List<EducationalResource> getContentsViewed() {
        return contentsViewed;
    }

    public List<String> getLearningGoalsReachedTracked() {
        return learningGoalsReachedTracked;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }
    
}
