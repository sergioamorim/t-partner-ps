/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author sergio
 */
@Entity
@Table(name="client")
public class Client implements Serializable {
    @Id
    @SequenceGenerator(name="client_id_seq",initialValue=1,
    allocationSize=1,sequenceName="client_id_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="client_id_seq")
    private Integer id;

    @Email(message="Invalid email format")
    @Column(name="email")
    private String email;

    public Client(){

    }

    public int getId() {
            return id;
    }

    public void setId(int id) {
            this.id = id;
    }

    public String getEmail() {
            return email;
    }

    public void setEmail(String email) {
            this.email = email;
    }
    
}
