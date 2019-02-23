package com.revature.sjm.domain;

import java.util.ArrayList;
import java.util.List;

import com.revature.sjm.BankApp;
import com.revature.sjm.abstraction.TransactionRequest;
import com.revature.sjm.service.TransactionManager;

public class GetCustomerTransactions extends TransactionRequest {

	private Customer customer;
	private Account account;
	private List<Transaction> transactions;

	public GetCustomerTransactions(Customer customer, Account account) {
		this.customer = customer;
		this.account = account;
		this.transactions = new ArrayList<>();
	}

	@Override
	public void require() {
		TransactionManager manager = super.getTransactionManager();
		int custId = customer.getCustId();
		int bankId = BankApp.bank.getBankId();
		int acntId = account.getAcntId();
		transactions.addAll(manager.findCustomerTransactions(custId, bankId, acntId));
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}
}
