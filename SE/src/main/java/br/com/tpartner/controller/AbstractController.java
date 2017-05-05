/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tpartner.controller;

import br.com.tpartner.exceptions.ValidationException;
import br.com.tpartner.repository.interfaces.RepositoryInterface;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author sergio
 * @param <T>
 * @param <ID>
 */
@Transactional
public abstract class AbstractController<T, ID> {

	protected abstract RepositoryInterface<T, ID> getRepository();

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<T> list() {
		return getRepository().findAll();
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public T getById(@PathVariable ID id) {
		return getRepository().findById(id);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable ID id) {
		T entity = getRepository().findById(id);
		getRepository().delete(entity);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public T save(@RequestBody @Valid T newObject, BindingResult result,
			HttpServletResponse response) throws JsonParseException,
			JsonMappingException, IOException {
		if (result.hasErrors()) {
			throw new ValidationException(result);
		}

		return getRepository().save(newObject);
	}

	
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public T update(@RequestBody @Valid T newObject, BindingResult result,
			HttpServletResponse response) throws JsonParseException,
			JsonMappingException, IOException {
		if (result.hasErrors()) {
			throw new ValidationException(result);
		}

		return getRepository().update(newObject);
	}
	@SuppressWarnings("unchecked")
	protected Class<T> getEntityClass() {
		return (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
}