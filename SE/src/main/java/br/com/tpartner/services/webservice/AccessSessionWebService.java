/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.services.webservice;

import br.com.tpartner.persistence.model.AccessSession;
import br.com.tpartner.persistence.model.Student;
import br.com.tpartner.services.facade.AccessSessionFacade;
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

/**
 *
 * @author sergio
 */
@Controller
@RequestMapping("/access_session")
public class AccessSessionWebService {
    @Autowired
    private AccessSessionFacade accessSessionFacade;
    public String serviceType = "AccessSession Service";
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Serializable> save(@RequestBody AccessSession accessSession) {
        AccessSession a = this.accessSessionFacade.save(accessSession);
        return new ResponseEntity<Serializable>(a, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<Serializable> update(@RequestBody AccessSession accessSession) {
        AccessSession a = this.accessSessionFacade.update(accessSession);
        return new ResponseEntity<Serializable>(a, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody ResponseEntity<Serializable> delete(@RequestParam(value = "id", required = true) Integer id) {
        this.accessSessionFacade.delete(this.accessSessionFacade.findById(id));
        return new ResponseEntity<Serializable>(serviceType+": AccessSession deletado com sucesso!",HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Serializable> findById(@RequestParam(value = "id", required = true) Integer id) {
        AccessSession a = this.accessSessionFacade.findById(id);
        return new ResponseEntity<Serializable>(a,HttpStatus.OK);
    }    
    
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public @ResponseBody List<AccessSession> findByStudent(@RequestParam(value = "student", required = true) Student student) {
        return this.accessSessionFacade.findByStudent(student);
    }
    
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public @ResponseBody List<AccessSession> findAll() {
        return this.accessSessionFacade.findAll();
    }
}
