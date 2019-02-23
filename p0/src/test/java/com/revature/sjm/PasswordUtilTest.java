package com.revature.sjm;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.sjm.util.PasswordUtil;

public class PasswordUtilTest {

	@BeforeClass
	public static void setUp() {
	}

	@Test
	public void custPasswordEqualsHashedPasswordTest() {
		String custPassword = "cecelia_knox";
		String salt = PasswordUtil.getSalt();
		String hashedPassword = PasswordUtil.getHash(custPassword, salt);
		boolean isAMatch = PasswordUtil.checkPassword(custPassword, hashedPassword);
		assertTrue(isAMatch);
	}

	@Test
	public void logInTest() {
		String custPassword = "stevemcqueen";
		boolean isAMatch = PasswordUtil.checkPassword(custPassword,
				"$2a$12$.PGtES.TQVJTQnePRL0zTuqHkBXfNFLJHjlQu20SiiqOO6FY9ncTa");
		assertTrue(isAMatch);
	}
}
