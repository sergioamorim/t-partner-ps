package br.com.tpartner.controller.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tpartner.controller.AbstractController;
import br.com.tpartner.model.infrastructure.User;
import br.com.tpartner.repository.interfaces.infrastructure.UserRepositoryInterface;

@RestController
@Transactional
@RequestMapping(value = "/user")
public class UserController extends AbstractController<User, Integer>  {
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepositoryInterface repository;
	
	@Override
	protected UserRepositoryInterface getRepository() {
		return this.repository;
	}

}
