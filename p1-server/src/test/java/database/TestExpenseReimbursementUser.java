package database;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.ExpenseReimbursementUserDAO;
import entity.ExpenseReimbursementUser;

public class TestExpenseReimbursementUser {

	private static ExpenseReimbursementUserDAO dao;

	@BeforeClass
	public static void setup() {
		dao = new ExpenseReimbursementUserDAO();
	}

	@Test
	public void testExpenseReimbursementUser() {
		ExpenseReimbursementUser user = dao.findBy(1);
		Assert.assertNotNull(user);
	}

	@Test
	public void testExpenseReimbursementUserIdByUsernamePassword() {
		String username = "steve";
		String password = "$2a$12$7HLwW4j/LSlfVrt0kq1J9e0EXj8a2duhtMPf07E7laa0kS2Mo9nCC";
		int id = dao.findExpenseReimbursementUserIdBy(username, password);
		Assert.assertNotEquals(0, id);
	}
}
