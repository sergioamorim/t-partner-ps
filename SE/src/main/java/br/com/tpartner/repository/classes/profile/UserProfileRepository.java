package br.com.tpartner.repository.classes.profile;


import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.com.tpartner.model.profile.UserProfile;
import br.com.tpartner.repository.classes.GenericHibernateRepository;
import br.com.tpartner.repository.interfaces.profile.UserProfileRepositoryInterface;

@Component
public class UserProfileRepository extends GenericHibernateRepository<UserProfile, Integer>
				implements UserProfileRepositoryInterface{
        @Override
	public UserProfile findUserProfileById(Integer x) {
		try{
			return this.findByCriteria(Restrictions.eq("user.id", x)).get(0);	
		}catch(IndexOutOfBoundsException e){
			return null;
		}
	}	
	
        @Override
	public List<UserProfile> findUserProfileByName(String name){
		return this.findByCriteria(Restrictions.like("name", "%"+name+"%"));	
		
	}

}
