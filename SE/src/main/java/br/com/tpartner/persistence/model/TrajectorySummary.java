/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.model;

import br.com.tpartner.persistence.crud.AccessSessionCRUD;
import br.com.tpartner.persistence.crud.StudentActionCRUD;
import br.com.tpartner.persistence.crud.SubSessionCRUD;
import br.com.tpartner.persistence.model.AccessSession;
import br.com.tpartner.persistence.model.Student;
import br.com.tpartner.persistence.model.StudentAction;
import br.com.tpartner.persistence.model.SubSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author sergio
 */

public class TrajectorySummary implements Serializable {
    private final Student student;
    private Integer hitsTotal;
    private Integer failsTotal;
    private Integer problemSolvingTotalTime;
    private List<String> contentsViewed;
    private Integer contentViewTotalTime;
    private Double contentViewAverageTime;
    private String lastLevelReached;
    private Integer newLevelsReached;
    private List<String> problemsTried;
    private Double triesToHitAverage;
    private Double viewsPerContent;
    private Double triesPerProblem;
    private Integer dummyTestQuestionsDone;
    private List<String> learningGoalsReached;
    private List<SubSession> subSessions;
    private List<StudentAction> studentActions;
    private Integer contentsRepeated;
    private Integer problemsRepeated;
    private Double problemSolvingAverageTime;
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
        this.triesToHitAverage = 0.0;
        this.dummyTestQuestionsDone = 0;
        this.lastLevelReached = "?";
        this.contentsViewed = new ArrayList<String>();
        this.problemsTried = new ArrayList<String>();
        this.learningGoalsReached = new ArrayList<String>();
        this.subSessions = new ArrayList<SubSession>();
        this.studentActions = new ArrayList<StudentAction>();
        this.run();
        this.triesToHitAverage = (double) (this.hitsTotal+this.failsTotal) / this.problemsTried.size();
        this.contentViewAverageTime = (double) this.contentViewTotalTime / this.contentsViewed.size();
        this.problemSolvingAverageTime = (double) this.problemSolvingTotalTime / this.problemsTried.size();
        this.viewsPerContent = (double) (this.contentsRepeated + this.contentsViewed.size()) / this.contentsViewed.size();
        this.triesPerProblem = (double) (this.problemsRepeated + this.problemsTried.size()) / this.problemsTried.size();
        System.out.println(student);
        System.out.println(timeStart);
        System.out.println(timeEnd);
        System.out.println(hitsTotal);
        System.out.println(failsTotal);
        System.out.println(problemSolvingAverageTime);
        System.out.println(problemSolvingTotalTime);
        System.out.println(contentViewAverageTime);
        System.out.println(contentViewTotalTime);
        System.out.println(contentsRepeated);
        System.out.println(problemsRepeated);
        System.out.println(newLevelsReached);
        System.out.println(triesPerProblem);
        System.out.println(triesToHitAverage);
        System.out.println(dummyTestQuestionsDone);
        System.out.println(lastLevelReached);
        System.out.println(contentsViewed);
        System.out.println(problemsTried);
        System.out.println(learningGoalsReached);
        System.out.println(subSessions);
        System.out.println(studentActions);
        System.out.println(viewsPerContent);
    }

    private void run() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        AccessSessionCRUD accessSessionDAO = context.getBean(AccessSessionCRUD.class);
        List<AccessSession> accessSessions = accessSessionDAO.findByStudent(this.student);
        SubSessionCRUD subSessionDAO = context.getBean(SubSessionCRUD.class);
        StudentActionCRUD studentActionDAO = context.getBean(StudentActionCRUD.class);
        List<SubSession> subSessions = new ArrayList<SubSession>();
        for (AccessSession accessSession : accessSessions) {
            if (accessSession.getTimeStart().before(this.timeEnd)) {
                subSessions.addAll(subSessionDAO.findByAccessSession(accessSession));
            }
        }
        for (SubSession subSession : subSessions) {
            if(subSession.getTimeStart().after(this.timeStart) && subSession.getTimeStart().before(this.timeEnd)) {
                this.subSessions.add(subSession);
                for (StudentAction studentAction : studentActionDAO.findBySubSession(subSession)) {
                    this.studentActions.add(studentAction);
                    if (studentAction.getProblemCorrectlyDone().equals("1") || studentAction.getProblemCorrectlyDone().equals("0")) {
                        if(studentAction.getProblemCorrectlyDone().equals("1")) {
                            this.hitsTotal++;
                        }
                        else {
                            this.failsTotal++;
                        }
                        if (!this.problemsTried.contains(studentAction.getProblemId())) {
                            this.problemsTried.add(studentAction.getProblemId());
                        }
                        else {
                            this.problemsRepeated++;
                        }
                        this.problemSolvingTotalTime += Integer.parseInt(studentAction.getProblemResponseTime());
                    }
                    else if (!studentAction.getContentId().equals("*")) {
                        if (!this.contentsViewed.contains(studentAction.getContentId())) {
                            this.contentsViewed.add(studentAction.getContentId());
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
                        this.learningGoalsReached.add(studentAction.getlGoalCurriculum());
                    }
                }
            }
        }
    }
}
