/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.repository.interfaces.se;

import br.com.tpartner.model.se.Action;
import br.com.tpartner.model.se.SessionA;
import br.com.tpartner.model.se.Student;
import br.com.tpartner.repository.interfaces.RepositoryInterface;
import java.util.List;

/**
 *
 * @author sergio
 */
public interface ActionRepositoryInterface extends RepositoryInterface<Action, Long> {
    public List<Action> findActionsByStudent(Student student);
    public List<Action> findActionsBySessionA (SessionA sessionA);
}
