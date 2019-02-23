package com.revature.sjm.abstraction;

import com.revature.sjm.service.AccountManager;
import com.revature.sjm.service.TransactionManager;

public abstract class TransactionRequest extends AbstractRequest {

	private AccountManager accountManager;
	private TransactionManager transactionManager;

	public TransactionRequest() {
		accountManager = new AccountManager();
		transactionManager = new TransactionManager();
	}

	public AccountManager getAccountManager() {
		return accountManager;
	}

	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	public TransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(TransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

}
