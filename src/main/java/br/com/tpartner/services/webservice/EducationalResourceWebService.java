/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.services.webservice;

import br.com.tpartner.persistence.model.EducationalResource;
import br.com.tpartner.persistence.model.Student;
import br.com.tpartner.services.facade.EducationalResourceFacade;
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
@RequestMapping("/educational_resource")
public class EducationalResourceWebService {
    @Autowired
    private EducationalResourceFacade educationalResourceFacade;
    public String serviceType = "EducationalResource Service";
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Serializable> save(@RequestBody EducationalResource educationalResource) {
        EducationalResource a = this.educationalResourceFacade.save(educationalResource);
        return new ResponseEntity<Serializable>(a, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<Serializable> update(@RequestBody EducationalResource educationalResource) {
        EducationalResource a = this.educationalResourceFacade.update(educationalResource);
        return new ResponseEntity<Serializable>(a, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody ResponseEntity<Serializable> delete(@RequestParam(value = "id", required = true) String id) {
        this.educationalResourceFacade.delete(this.educationalResourceFacade.findById(id));
        return new ResponseEntity<Serializable>(serviceType+": EducationalResource deleted",HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Serializable> findById(@RequestParam(value = "id", required = true) String id) {
        EducationalResource a = this.educationalResourceFacade.findById(id);
        return new ResponseEntity<Serializable>(a,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public @ResponseBody List<EducationalResource> findAll() {
        return this.educationalResourceFacade.findAll();
    }
}
