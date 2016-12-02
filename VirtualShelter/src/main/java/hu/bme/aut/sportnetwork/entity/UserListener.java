package hu.bme.aut.sportnetwork.entity;

import javax.persistence.PrePersist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.bme.aut.sportnetwork.auth.AuthOperations;

public class UserListener {

	private static Logger LOGGER = LoggerFactory.getLogger(UserListener.class);

	@PrePersist
	public void generatePassword(User u) {
		byte[] salt = AuthOperations.getSalt();
		String securePassword = AuthOperations.get_SHA_1_SecurePassword(u.getPassword(), salt);
		u.setPassword(securePassword);
		u.setSalt(AuthOperations.toHexString(salt));
		LOGGER.info("New user saved with name" + u.getName());
	}


}
