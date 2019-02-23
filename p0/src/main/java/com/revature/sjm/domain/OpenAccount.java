package com.revature.sjm.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.revature.sjm.abstraction.AccountManagementRequest;
import com.revature.sjm.service.AccountManager;

public class OpenAccount extends AccountManagementRequest {

	private List<Customer> customers;
	private Account account;

	public OpenAccount(List<Customer> customers, Account account) {
		this.customers = customers;
		this.account = account;
	}

	@Override
	public void require() {
		AccountManager manager = super.getAccountManager();
		Account newAccount = manager.insert(account);
		for (int i = 0; i < customers.size(); i++) {
			manager.insertCustomerOwnedAccount(newAccount.getAcntId(), customers.get(i).getCustId(),
					Timestamp.valueOf(LocalDateTime.now()));
		}
		System.out.println("Created: " + newAccount);
	}
}
