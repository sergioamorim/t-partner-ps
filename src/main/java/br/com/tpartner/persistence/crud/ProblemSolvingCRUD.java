/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.crud;

import br.com.tpartner.persistence.model.ProblemSolving;
import java.util.List;

/**
 *
 * @author sergio
 */
public interface ProblemSolvingCRUD {
    /* Client */
    public ProblemSolving save(ProblemSolving problemSolving);
    public ProblemSolving update(ProblemSolving problemSolving);
    public void delete(ProblemSolving problemSolving);
    /* Aux */
    public ProblemSolving findById(Integer problemSolvingId);
    public List<ProblemSolving> findAll();
}
