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
    private Double score;
    private Double triesToHitAverage;
    private Double viewsPerContent;
    private Double triesPerProblem;
    private Double problemSolvingAverageTime;
    private Double contentViewAverageTime;
    private Double actionsPerSubSessionAverage;
    private Double performanceMeasure;
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
        score = 0.0;
        hitsTotal = 0;
        failsTotal = 0;
        problemSolvingTotalTime = 0;
        contentViewTotalTime = 0;
        contentsRepeated = 0;
        problemsRepeated = 0;
        newLevelsReached = 0;
        dummyTestQuestionsDone = 0;
        run();
    }

    private void run() {
        
        AbstractApplicationContext context;
        context = new ClassPathXmlApplicationContext("spring.xml");
        
        AccessSessionCRUD accessSessionDAO;
        accessSessionDAO = context.getBean(AccessSessionCRUD.class);
        
        List<AccessSession> accessSessions;
        accessSessions = accessSessionDAO.findByStudent(student);
        
        SubSessionCRUD subSessionDAO = context.getBean(SubSessionCRUD.class);
        
        StudentActionCRUD studentActionDAO;
        studentActionDAO = context.getBean(StudentActionCRUD.class);
        
        List<SubSession> subSessions = new ArrayList<SubSession>();
        subSessionsTracked = new ArrayList<SubSession>();
        studentActionsTracked = new ArrayList<StudentAction>();
        problemsTried = new ArrayList<EducationalResource>();
        contentsViewed = new ArrayList<EducationalResource>();
        learningGoalsReachedTracked = new ArrayList<String>();
        for (AccessSession accessSession : accessSessions) {
            if (accessSession.getTimeStart().before(timeEnd)) {
                
                subSessions.addAll(subSessionDAO.findByAccessSession(
                        accessSession));
                
            }
        }
        for (SubSession subSession : subSessions) {
            if(subSession.getTimeStart().after(timeStart) &&
                    subSession.getTimeStart().before(timeEnd)) {
                subSessionsTracked.add(subSession);
                
                for (StudentAction studentAction : studentActionDAO.
                        findBySubSession(subSession)) {
                    
                    studentActionsTracked.add(studentAction);
                    if (studentAction.getClass() == ProblemSolving.class) {
                        ProblemSolving problemSolving;
                        problemSolving = (ProblemSolving) studentAction;
                        if(problemSolving.getCorrectlyDone()) {
                            hitsTotal++;
                        }
                        else {
                            failsTotal++;
                        }
                        
                        EducationalResource educationalResource =
                                problemSolving.getEducationalResource();
                        
                        if (!problemsTried.contains(educationalResource)) {
                            problemsTried.add(educationalResource);
                        }
                        else {
                            problemsRepeated++;
                        }
                        problemSolvingTotalTime +=
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
                            contentsRepeated++;
                        }
                        contentViewTotalTime +=
                                resourceInteraction.getTimeSpent();
                    }
                    else {
                        
                        NonMappedStudentAction nonMappedStudentAction;
                        nonMappedStudentAction = 
                                (NonMappedStudentAction) studentAction;
                        
                        if (!nonMappedStudentAction.getGamificationLevel().
                                equals("*")) {
                            
                            newLevelsReached++;
                            
                            score += Integer.parseInt(
                                    nonMappedStudentAction.getNumberOfPoints());
                            
                            lastLevelReached = 
                                nonMappedStudentAction.getGamificationLevel();
                            
                        }
                        else if (nonMappedStudentAction.getType().equals(
                                "PROBLEM_BASED_EVALUATION")){
                            dummyTestQuestionsDone++;
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
        problemsTriedTotal = failsTotal + hitsTotal;
        contentsViewedTotal = contentsViewed.size();
        
        triesToHitAverage = 
                (double) problemsTriedTotal / hitsTotal;
        
        contentViewAverageTime = 
                (double) contentViewTotalTime / contentsViewedTotal;
        
        problemSolvingAverageTime = 
                (double) problemSolvingTotalTime / problemsTriedTotal;
        
        viewsPerContent = 
                (double) (contentsRepeated + contentsViewedTotal) /
                    contentsViewedTotal;
        
        triesPerProblem = (double) (problemsRepeated + problemsTriedTotal) /
                    problemsTriedTotal;
        
        learningGoalsReachedTotal = learningGoalsReachedTracked.size();
        studentActionsTotal = studentActionsTracked.size();
        subSessionsTotal = subSessionsTracked.size();
        
        actionsPerSubSessionAverage = 
                (double) studentActionsTotal / subSessionsTotal;
        if (problemsTriedTotal != 0) {
            performanceMeasure = (double) hitsTotal / problemsTriedTotal;
            if (score > 0) {
                performanceMeasure *= score;
            }
        }
        else {
            performanceMeasure = 0.0;
        }
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

    public Double getScore() {
        return score;
    }

    public Double getPerformanceMeasure() {
        return performanceMeasure;
    }
    
}
