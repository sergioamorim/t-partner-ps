/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.services.webservice;

import br.com.tpartner.persistence.model.GamingTheSystem;
import br.com.tpartner.persistence.model.SubSession;
import br.com.tpartner.persistence.model.StudentAction;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import br.com.tpartner.services.facade.StudentActionFacade;

/**
 *
 * @author sergio
 */
@Controller
@RequestMapping("/student_action")
public class StudentActionWebService {
    @Autowired
    private StudentActionFacade studentActionFacade;
    public String serviceType = "StudentAction Service";
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Serializable> save(
            @RequestBody StudentAction action) {
        
        StudentAction a = studentActionFacade.save(action);
        return new ResponseEntity<Serializable>(a, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<Serializable> update(
            @RequestBody StudentAction action) {
        
        StudentAction a = studentActionFacade.update(action);
        return new ResponseEntity<Serializable>(a, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody ResponseEntity<Serializable> delete(
            @RequestParam(value = "id", required = true) Integer id) {
        
        this.studentActionFacade.delete(studentActionFacade.findById(id));
        
        return new ResponseEntity<Serializable>(
                serviceType+": StudentAction deleted",HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<StudentAction> findById(@RequestParam(
            value = "id", required = true) Integer id) {
        
        StudentAction a = studentActionFacade.findById(id);
        return new ResponseEntity<StudentAction>(a,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/sub_session", method = RequestMethod.GET)
    public @ResponseBody List<StudentAction> findBySubSession(@RequestParam(
            value = "subSession", required = true) SubSession subSession) {
        
        return studentActionFacade.findBySubSession(subSession);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<StudentAction> findAll() {
        return studentActionFacade.findAll();
    }
    
    @RequestMapping(value = "/gaming_the_system", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Serializable> getGamingTheSystem() {
        GamingTheSystem a = studentActionFacade.getGamingTheSystem();
        return new ResponseEntity<Serializable>(a,HttpStatus.OK);
    }
}
