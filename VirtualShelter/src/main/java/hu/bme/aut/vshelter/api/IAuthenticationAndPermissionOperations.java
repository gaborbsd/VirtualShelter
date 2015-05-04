package hu.bme.aut.vshelter.api;

public interface IAuthenticationAndPermissionOperations {
	
	/**
	 * Returns whether the user's login was successful or not
	 * @param email
	 * @param password
	 * @return
	 */
	boolean authenticate(String email, String password);
}
