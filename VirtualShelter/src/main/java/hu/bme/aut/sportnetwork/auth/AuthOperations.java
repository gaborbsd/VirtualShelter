package hu.bme.aut.sportnetwork.auth;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import hu.bme.aut.sportnetwork.dal.UserDAO;
import hu.bme.aut.sportnetwork.entity.User;

public class AuthOperations {

	@Autowired
	private UserDAO userRepository;
	
	final private static Character[] hexArray = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
			'E', 'F' };

	public User getLoggedInUser() {
		String name = getLoggedInUserName();
		User u = userRepository.findByName(name);
		if (u == null) {
			u = userRepository.findByEmail(name);
		}
		return u;
	}
	
	public String getLoggedInUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	public static String get_SHA_1_SecurePassword(String passwordToHash, byte[] salt) {
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(salt);
			byte[] bytes = md.digest(passwordToHash.getBytes());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}

	public static String toHexString(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

	public static byte[] toByteArray(String hexa) {
		byte[] ret = new byte[16];

		for (int k = 0; k < 32 && k < hexa.length(); k += 2) {
			int i = java.util.Arrays.asList(hexArray).indexOf(hexa.charAt(k));
			int j = java.util.Arrays.asList(hexArray).indexOf(hexa.charAt(k + 1));

			int newb = 16 * i + j;
			ret[k / 2] = (byte) newb;
		}
		return ret;
	}

	public static byte[] getSalt() {
		SecureRandom sr;
		byte[] salt = null;
		try {
			sr = SecureRandom.getInstance("SHA1PRNG");
			salt = new byte[16];
			sr.nextBytes(salt);
			return salt;
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
}
