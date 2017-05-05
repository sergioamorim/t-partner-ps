/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.repository.classes.se;

import br.com.tpartner.model.se.Action;
import br.com.tpartner.model.se.SessionA;
import br.com.tpartner.model.se.Student;
import br.com.tpartner.repository.classes.GenericHibernateRepository;
import br.com.tpartner.repository.interfaces.se.ActionRepositoryInterface;
import java.util.List;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

/**
 *
 * @author sergio
 */
@Component
public class ActionRepository extends GenericHibernateRepository<Action, Long> implements ActionRepositoryInterface {

    public ActionRepository(Session session) {
    }

    @Override
    public List<Action> findActionsByStudent(Student student) {
        ActionRepository actionRepository = new ActionRepository(this.getSession());
        List<SessionA> studendSessionsA = student.getSessions();
        List<Action> studentActions = null;
        for (SessionA studentSessionA : studendSessionsA){
            boolean addAll = studentActions.addAll(studentSessionA.getActions());
        }
        return studentActions;
    }

    @Override
    public List<Action> findActionsBySessionA(SessionA sessionA) {
        return sessionA.getActions();
    }
    
}
