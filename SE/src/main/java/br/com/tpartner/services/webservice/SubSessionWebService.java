/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.services.webservice;

import br.com.tpartner.persistence.model.AccessSession;
import br.com.tpartner.persistence.model.SubSession;
import br.com.tpartner.services.facade.SubSessionFacade;
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
@RequestMapping("/sub_session")
public class SubSessionWebService {
    @Autowired
    private SubSessionFacade subSessionFacade;
    public String serviceType = "SubSession Service";
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Serializable> save(@RequestBody SubSession subSession) {
        SubSession a = this.subSessionFacade.save(subSession);
        return new ResponseEntity<Serializable>(a, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<Serializable> update(@RequestBody SubSession subSession) {
        SubSession a = this.subSessionFacade.update(subSession);
        return new ResponseEntity<Serializable>(a, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody ResponseEntity<Serializable> delete(@RequestParam(value = "id", required = true) Integer id) {
        this.subSessionFacade.delete(this.subSessionFacade.findById(id));
        return new ResponseEntity<Serializable>(serviceType+": SubSession deleted",HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Serializable> findById(@RequestParam(value = "id", required = true) Integer id) {
        SubSession a = this.subSessionFacade.findById(id);
        return new ResponseEntity<Serializable>(a,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/access_session", method = RequestMethod.GET)
    public @ResponseBody List<SubSession> findByAccessSession(@RequestParam(value = "accessSession", required = true) AccessSession accessSession) {
        return this.subSessionFacade.findByAccessSession(accessSession);
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public @ResponseBody List<SubSession> findAll() {
        return this.subSessionFacade.findAll();
    }
}
