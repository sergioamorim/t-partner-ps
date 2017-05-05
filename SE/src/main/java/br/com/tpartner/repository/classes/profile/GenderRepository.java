package br.com.tpartner.repository.classes.profile;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.tpartner.model.profile.Gender;
import br.com.tpartner.repository.classes.GenericHibernateRepository;
import br.com.tpartner.repository.interfaces.profile.GenderRepositoryInterface;

@Component
public class GenderRepository extends GenericHibernateRepository<Gender, Integer>
				implements GenderRepositoryInterface{
	public void initializeGenders(){
		List<Gender> u =  new ArrayList<>();
		u.add(new Gender("Masculino"));
		u.add(new Gender("Feminino"));
		for (Gender x : u){
			save(x);
		}
	}

}
