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
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

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
    private int id;

    @NotNull(message = "O nome não pode ser nulo")
    @NotEmpty(message = "Nome do client está vazio")
    @Column(name="name")
    private String name;

    @NotNull(message = "O CPF não pode ser nulo")
    @Size(min=11, max = 11, message = "CPF tem que ter 11 digitos")
    @Column(name="cpf", unique=true)
    private String cpf;

    @NotNull(message = "O endereço não pode ser nulo")
    @Column(name="address")
    private String address;

    @Digits(message = "Apenas digitos no número de telefone ", integer = 10, fraction = 0)
    @Column(name="phone_number")
    private String phone_number;

    @Email(message="Email não válido - fora do formato")
    @Column(name="email")
    private String email;

    @Column(name="type_client")
    private String type_client;


    public Client(){

    }

    public int getId() {
            return id;
    }

    public void setId(int id) {
            this.id = id;
    }

    public String getName() {
            return name;
    }

    public void setName(String name) {
            this.name = name;
    }

    public String getAddress() {
            return address;
    }

    public void setAddress(String address) {
            this.address = address;
    }

    public String getPhone_number() {
            return phone_number;
    }

    public void setPhone_number(String phone_number) {
            this.phone_number = phone_number;
    }

    public String getEmail() {
            return email;
    }

    public void setEmail(String email) {
            this.email = email;
    }

    public String getCpf() {
            return cpf;
    }

    public void setCpf(String cpf) {
            this.cpf = cpf;
    }

    public String getType_client() {
            return type_client;
    }

    public void setType_client(String type_client) {
            this.type_client = type_client;
    }
}
