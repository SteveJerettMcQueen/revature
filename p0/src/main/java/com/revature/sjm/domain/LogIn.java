package com.revature.sjm.domain;

import com.revature.sjm.BankApp;
import com.revature.sjm.abstraction.RegistrationRequest;
import com.revature.sjm.service.CustomerManager;
import com.revature.sjm.util.PasswordUtil;

public class LogIn extends RegistrationRequest {

	private String userName;
	private String password;
	private Customer customer;

	public LogIn(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	@Override
	public void require() {
		if (check(userName, password)) {
			CustomerManager manager = super.getCustomerManager();
			String salt = manager.findCustomerSaltForPasswordCheck(userName);
			customer = manager.findCustomerByUserNameAndPassword(userName, PasswordUtil.getHash(password, salt));
		} else {
			System.out.println("Incorrect username or password, try again!");
		}
	}

	public Customer getCustomer() {
		return customer;
	}

	private boolean check(String userName, String password) {
		CustomerManager manager = super.getCustomerManager();
		String passwordInDB = manager.findCustomerPasswordByUserName(BankApp.bank.getBankId(), userName);
		if (passwordInDB != null) {
			return isMatch(password, passwordInDB);
		}
		return false;
	}

	private boolean isMatch(String inputPassword, String hashedPassword) {
		return PasswordUtil.checkPassword(inputPassword, hashedPassword);
	}
}
