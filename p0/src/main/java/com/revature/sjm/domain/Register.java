package com.revature.sjm.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.revature.sjm.BankApp;
import com.revature.sjm.abstraction.RegistrationRequest;
import com.revature.sjm.service.CustomerManager;
import com.revature.sjm.util.PasswordUtil;

public class Register extends RegistrationRequest {

	private Customer customer;

	public Register(Customer customer) {
		this.customer = customer;
	}

	@Override
	public void require() {
		customer = insert(customer);

		int bankId = BankApp.bank.getBankId();
		int custId = customer.getCustId();
		String userName = customer.getUserName();
		String salt = PasswordUtil.getSalt();
		String inputPassword = customer.getPassword();
		String hashedPassword = PasswordUtil.getHash(inputPassword, salt);
		Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

		insertBankRegisteredCustomer(bankId, custId, timestamp);
		insertCustomerAccessWithBank(custId, bankId, userName, hashedPassword, salt);
		System.out.println("Registered: " + customer);
	}

	public Customer getRegisteredCustomer() {
		return customer;
	}

	private Customer insert(Customer customer) {
		CustomerManager manager = super.getCustomerManager();
		return manager.insert(customer);
	}

	private void insertBankRegisteredCustomer(int bankId, int custId, Timestamp timestamp) {
		CustomerManager manager = super.getCustomerManager();
		manager.insertBankRegisteredCustomer(bankId, custId, timestamp);
	}

	private void insertCustomerAccessWithBank(int custId, int bankId, String userName, String password, String salt) {
		CustomerManager manager = super.getCustomerManager();
		manager.insertCustomerAccessWithBank(custId, bankId, userName, password, salt);
	}
}
