package br.com.tpartner.repository.interfaces.profile;

import java.util.List;

import br.com.tpartner.model.profile.UserProfile;
import br.com.tpartner.repository.interfaces.RepositoryInterface;

public interface UserProfileRepositoryInterface extends RepositoryInterface<UserProfile, Integer>{

	public UserProfile findUserProfileById(Integer x);
	
	public List<UserProfile> findUserProfileByName(String name);
}
