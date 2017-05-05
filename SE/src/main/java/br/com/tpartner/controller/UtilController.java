package br.com.tpartner.controller;

import br.com.tpartner.model.profile.Gender;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.tpartner.model.se.Action;
import br.com.tpartner.model.se.SessionA;
import br.com.tpartner.model.se.Student;
import br.com.tpartner.repository.interfaces.profile.GenderRepositoryInterface;
import br.com.tpartner.repository.interfaces.se.ActionRepositoryInterface;
import br.com.tpartner.repository.interfaces.se.SessionARepositoryInterface;
import br.com.tpartner.repository.interfaces.se.StudentRepositoryInterface;

@RestController
@Transactional
@RequestMapping(value="/util_controller")
public class UtilController {
	
	@Autowired
	@Qualifier("genderRepository")
	private GenderRepositoryInterface genderRepository;
	
	@RequestMapping(value="/init_genders")
	@ResponseBody
	public void initializeGenders(){
		List<Gender> gl = getGenderRepository().findAll();
		if (gl.isEmpty()){
			getGenderRepository().initializeGenders();
		}
	}
	
	@RequestMapping(value="/init_all")
	@ResponseBody
	public void initializeAll(){
		initializeGenders();
	}

	public GenderRepositoryInterface getGenderRepository() {
		return genderRepository;
	}

	public void setGenderRepository(GenderRepositoryInterface genderRepository) {
		this.genderRepository = genderRepository;
	}

}
