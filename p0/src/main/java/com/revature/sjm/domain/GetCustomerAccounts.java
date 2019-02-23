package com.revature.sjm.domain;

import java.util.ArrayList;
import java.util.List;

import com.revature.sjm.abstraction.AccountManagementRequest;
import com.revature.sjm.service.AccountManager;

public class GetCustomerAccounts extends AccountManagementRequest {

	private Customer customer;
	private List<Account> accounts;

	public GetCustomerAccounts(Customer customer) {
		this.customer = customer;
		this.accounts = new ArrayList<>();
	}

	@Override
	public void require() {
		AccountManager manager = super.getAccountManager();
		accounts.addAll(manager.findCustomerAccountsById(customer.getCustId()));
	}

	public List<Account> getAccounts() {
		return accounts;
	}

}
