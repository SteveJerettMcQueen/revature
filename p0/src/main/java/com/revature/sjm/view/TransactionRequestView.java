package com.revature.sjm.view;

import java.math.BigDecimal;
import java.util.List;

import com.revature.sjm.controller.BankRequestController;
import com.revature.sjm.domain.Account;
import com.revature.sjm.domain.Customer;
import com.revature.sjm.domain.Deposit;
import com.revature.sjm.domain.EntryForm;
import com.revature.sjm.domain.GetCustomerTransactions;
import com.revature.sjm.domain.MakeTransaction;
import com.revature.sjm.domain.Transaction;
import com.revature.sjm.domain.Transfer;
import com.revature.sjm.domain.Withdrawal;

public class TransactionRequestView {

	private Customer customer;
	private Account account;
	private BankRequestController controller;

	public TransactionRequestView(BankRequestController controller) {
		this.controller = controller;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<Transaction> showCustomerTransactions() {
		GetCustomerTransactions request = new GetCustomerTransactions(customer, account);
		controller.setRequest(request);
		controller.executeRequest();
		return request.getTransactions();
	}

	public void deposit(EntryForm form, double amount) {
		String descr = "Customer Deposit";
		Transaction trans = new Deposit(descr, form, new BigDecimal(amount), account);
		controller.setRequest(new MakeTransaction(customer, trans));
		controller.executeRequest();
	}

	public void withdraw(EntryForm form, double amount) {
		String descr = "Customer Withdrawal";
		Transaction trans = new Withdrawal(descr, form, new BigDecimal(amount), account);
		controller.setRequest(new MakeTransaction(customer, trans));
		controller.executeRequest();
	}

	public void transfer(EntryForm form, double amount, Account targetAccount) {
		String descr = "Customer Transfer";
		Transaction trans = new Transfer(descr, form, new BigDecimal(amount), account, targetAccount);
		controller.setRequest(new MakeTransaction(customer, trans));
		controller.executeRequest();
	}
}
