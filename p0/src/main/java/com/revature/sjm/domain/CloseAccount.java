package com.revature.sjm.domain;

import com.revature.sjm.abstraction.AccountManagementRequest;
import com.revature.sjm.service.AccountManager;

public class CloseAccount extends AccountManagementRequest {

	private Account account;

	public CloseAccount(Account account) {
		this.account = account;
	}

	@Override
	public void require() {
		AccountManager manager = super.getAccountManager();
		manager.remove(account);
		System.out.println("Deleted: " + account);
	}

}
