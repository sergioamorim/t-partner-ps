/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.services.webservice;

import br.com.tpartner.persistence.crud.ClientCRUD;
import br.com.tpartner.persistence.model.Client;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author sergio
 */
@Controller
@RequestMapping("/test")
public class TestService {
    @Autowired
    ClientCRUD clientCRUD; 
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Serializable> createClient(@RequestBody Client client){
       Client c = clientCRUD.save(client);
       return new ResponseEntity<Serializable>(c, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody
    List<Client> clientFindAll(){
       return clientCRUD.findAll();
    }
}
