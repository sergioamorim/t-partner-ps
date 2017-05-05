package br.com.tpartner.controller.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tpartner.controller.AbstractController;
import br.com.tpartner.model.profile.Gender;
import br.com.tpartner.repository.interfaces.profile.GenderRepositoryInterface;

@RestController
@Transactional
@RequestMapping(value="/gender")
public class GenderController extends AbstractController<Gender, Integer>{

	@Autowired
	@Qualifier("genderRepository")
	private GenderRepositoryInterface repository;
	
	@Override
	protected GenderRepositoryInterface getRepository(){
		return this.repository;
	}
}
