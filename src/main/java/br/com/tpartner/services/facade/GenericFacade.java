/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.services.facade;

import java.util.List;

/**
 *
 * @author sergio
 */
public interface GenericFacade<T, ID> {
    public T save(T entity);
    public T update(T entity);
    public void delete(T entity);
    public T findById(ID id);
    public List<T> findAll();
}
