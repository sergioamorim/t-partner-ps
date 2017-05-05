/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.repository.interfaces.se;

import br.com.tpartner.repository.interfaces.RepositoryInterface;
import br.com.tpartner.model.se.SessionA;
import br.com.tpartner.model.se.Student;
import java.util.List;

/**
 *
 * @author sergio
 */
public interface SessionARepositoryInterface extends RepositoryInterface<SessionA, Long>{
    public List<SessionA> findSessionsAByStudent(Student student);
}
