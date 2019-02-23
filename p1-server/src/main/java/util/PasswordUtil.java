package util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

	public static void main(String[] args) {
		String password = "mary";
		String salt = getSalt();
		String hashedPassword = getHash(password, salt);
		System.out.println("hash: " + hashedPassword);
		System.out.println("salt: " + salt);
	}

	public static String getSalt() {
		return BCrypt.gensalt(12);
	}

	public static String getHash(String passwordToHash, String salt) {
		return BCrypt.hashpw(passwordToHash, salt);
	}

	public static boolean checkPassword(String inputPassword, String hashedPassword) {
		return BCrypt.checkpw(inputPassword, hashedPassword);

	}

}
