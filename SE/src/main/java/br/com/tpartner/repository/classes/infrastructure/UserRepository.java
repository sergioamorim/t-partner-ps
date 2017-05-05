package br.com.tpartner.repository.classes.infrastructure;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.com.tpartner.model.infrastructure.User;
import br.com.tpartner.repository.classes.GenericHibernateRepository;
import br.com.tpartner.repository.interfaces.infrastructure.UserRepositoryInterface;

@Component
public class UserRepository extends GenericHibernateRepository<User, Integer> 
				implements UserRepositoryInterface {
	
	public UserRepository(){
		
	}
	
	public UserRepository(Session session){
		this.setSession(session);
	}

	@Override
	public User getUserByUsername(Integer id) {
		return this.findByCriteria(Restrictions.eq("id", id)).get(0);
	}

}
