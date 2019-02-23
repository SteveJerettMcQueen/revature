package com.revature.sjm.abstraction;

import com.revature.sjm.service.AccountManager;

public abstract class AccountManagementRequest extends AbstractRequest {

	private AccountManager accountManager;

	public AccountManagementRequest() {
		accountManager = new AccountManager();
	}

	public AccountManager getAccountManager() {
		return accountManager;
	}

	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

}
