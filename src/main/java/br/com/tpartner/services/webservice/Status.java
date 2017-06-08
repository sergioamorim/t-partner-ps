/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.services.webservice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author sergio
 */
@Controller
@RequestMapping("/")
public class Status {
     
    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody String status(){
     
        return "<h1 style=\"text-align: center;\"><span style=\"font-size:16px;\"><strong>Services Online</strong></span></h1>";
    }
}
