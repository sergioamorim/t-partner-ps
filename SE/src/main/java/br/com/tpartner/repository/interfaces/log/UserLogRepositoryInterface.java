package br.com.tpartner.repository.interfaces.log;

import java.util.List;

import br.com.tpartner.model.log.UserLog;
import br.com.tpartner.model.infrastructure.User;
import br.com.tpartner.repository.interfaces.RepositoryInterface;

public interface UserLogRepositoryInterface extends RepositoryInterface<UserLog, Integer>{
	public List<UserLog> getLogs(User u);
}