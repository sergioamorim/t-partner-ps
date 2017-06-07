/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.services.controller;

import br.com.tpartner.persistence.crud.ProblemSolvingCRUD;
import br.com.tpartner.persistence.model.ProblemSolving;
import br.com.tpartner.services.facade.ProblemSolvingFacade;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author sergio
 */
public class ProblemSolvingController implements ProblemSolvingFacade {
    @Autowired
    ProblemSolvingCRUD problemSolvingCRUD;
    
    public ProblemSolving save(ProblemSolving problemSolving) {
        return this.problemSolvingCRUD.save(problemSolving);
    }

    public ProblemSolving update(ProblemSolving problemSolving) {
        return this.problemSolvingCRUD.update(problemSolving);
    }

    public void delete(ProblemSolving problemSolving) {
        this.problemSolvingCRUD.delete(problemSolving);
    }

    public ProblemSolving findById(Integer problemSolvingId) {
        return this.problemSolvingCRUD.findById(problemSolvingId);
    }

    public List<ProblemSolving> findAll() {
        return this.problemSolvingCRUD.findAll();
    }
}
