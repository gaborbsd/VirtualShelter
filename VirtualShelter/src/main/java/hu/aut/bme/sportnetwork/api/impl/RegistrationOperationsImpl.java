package hu.aut.bme.sportnetwork.api.impl;

import org.springframework.beans.factory.annotation.Autowired;

import hu.bme.aut.sportnetwork.api.RegistrationOperations;
import hu.bme.aut.sportnetwork.dal.UserDAO;
import hu.bme.aut.sportnetwork.entity.Address;
import hu.bme.aut.sportnetwork.entity.Rating;
import hu.bme.aut.sportnetwork.entity.Sports;
import hu.bme.aut.sportnetwork.entity.User;
import hu.bme.aut.sportnetwork.rest.resources.UserArg;

public class RegistrationOperationsImpl implements RegistrationOperations{
	
	@Autowired
	UserDAO userRepository;

	@Override
	public User registrate(UserArg arg) {
		return userRepository.saveNewUser((toUser(arg)));
	}

	private User toUser(UserArg arg) {
		User u = new User();
		if (arg.getAddress().getAddress() == null || (arg.getAddress().getAddress().isEmpty())) {
			arg.getAddress().setAddress(Address.EMPTY);
		}
		
		u.setAddress(arg.getAddress());
		u.setAdmin(false);
		u.setAge(arg.getAge());
		u.setEmail(arg.getEmail());
		u.setHasNotification(false);
		u.setHasWarning(false);
		u.setIntroduction(arg.getIntroduction());
		u.setName(arg.getName());
		u.setPassword(arg.getPassword());
		u.setPhoneNumber(arg.getPhoneNumber());
		arg.getInterest().forEach(s -> u.getRatings().add(createRating(u, s)));
		return u;
	}

	private Rating createRating(User u, Sports s) {
		Rating r = new Rating();
		r.setUser(u);
		r.setSport(s);
		r.setSumValue(0);
		r.setRateNumbers(0);
		return r;
	}
}
