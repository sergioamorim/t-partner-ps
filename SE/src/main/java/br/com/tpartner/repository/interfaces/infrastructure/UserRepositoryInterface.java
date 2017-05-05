package br.com.tpartner.repository.interfaces.infrastructure;

import br.com.tpartner.model.infrastructure.User;
import br.com.tpartner.repository.interfaces.RepositoryInterface;

public interface UserRepositoryInterface extends RepositoryInterface<User, Integer>{
	public User getUserByUsername(Integer id);
}
