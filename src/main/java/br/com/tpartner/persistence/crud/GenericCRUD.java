/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.persistence.crud;

import java.util.List;

/**
 *
 * @author sergio
 */
public abstract interface GenericCRUD<T, ID> {
    public T save(T entity);
    public T update(T entity);
    public void delete(T entity);
    public T findById(ID entityId);
    public List<T> findAll();
}