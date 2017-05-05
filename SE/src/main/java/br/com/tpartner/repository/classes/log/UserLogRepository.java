package br.com.tpartner.repository.classes.log;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.com.tpartner.model.infrastructure.User;
import br.com.tpartner.model.log.UserLog;
import br.com.tpartner.repository.classes.GenericHibernateRepository;
import br.com.tpartner.repository.interfaces.log.UserLogRepositoryInterface;

@Component
public class UserLogRepository extends GenericHibernateRepository<UserLog, Integer> implements UserLogRepositoryInterface{

	@Override
	public List<UserLog> getLogs(User u) {
		List<UserLog> userlogs = this.findByCriteria(Restrictions.eq("user", u));
		return userlogs;
	}

}
