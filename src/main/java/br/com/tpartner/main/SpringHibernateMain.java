/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.main;

import br.com.tpartner.persistence.crud.ClientCRUD;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author sergio
 */
public class SpringHibernateMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        System.out.println("Database OK");

        ClientCRUD clientDAO = context.getBean(ClientCRUD.class);
        context.close();
    }
}