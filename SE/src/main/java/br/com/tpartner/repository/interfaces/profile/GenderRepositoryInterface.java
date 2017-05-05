package br.com.tpartner.repository.interfaces.profile;

import br.com.tpartner.model.profile.Gender;
import br.com.tpartner.repository.interfaces.RepositoryInterface;

public interface GenderRepositoryInterface extends RepositoryInterface<Gender, Integer>{
	public void initializeGenders();
}
