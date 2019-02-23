package database;

import org.junit.Assert;
import org.junit.Test;

import util.PasswordUtil;

public class TestPasswordUtil {

	@Test
	public void inputPasswordEqualsHashedPasswordTest() {
		String inputPassword = "steve";
		String salt = PasswordUtil.getSalt();
		String hashedPassword = PasswordUtil.getHash(inputPassword, salt);
		boolean isAMatch = PasswordUtil.checkPassword(inputPassword, hashedPassword);
		Assert.assertTrue(isAMatch);
	}

	@Test
	public void inputPasswordMatchTest() {
		String inputPassword = "danny";
		boolean isAMatch = PasswordUtil.checkPassword(inputPassword,
				"$2a$12$wav8.QIHxPXTfMNp9rO2iOPN23TZoNUf0WPGqQ149qO1VWMTVAsjy");
		Assert.assertTrue(isAMatch);
	}

}
