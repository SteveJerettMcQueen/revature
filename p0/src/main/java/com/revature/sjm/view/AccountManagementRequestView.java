package com.revature.sjm.view;

import java.util.List;

import com.revature.sjm.controller.BankRequestController;
import com.revature.sjm.domain.Account;
import com.revature.sjm.domain.CloseAccount;
import com.revature.sjm.domain.Customer;
import com.revature.sjm.domain.GetCustomerAccounts;
import com.revature.sjm.domain.OpenAccount;

public class AccountManagementRequestView {

	private Customer customer;
	private Account account;
	private List<Customer> customers;
	private BankRequestController controller;

	public AccountManagementRequestView(BankRequestController controller) {
		this.controller = controller;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public List<Account> showCustomerAccounts() {
		GetCustomerAccounts request = new GetCustomerAccounts(customer);
		controller.setRequest(request);
		controller.executeRequest();
		return request.getAccounts();
	}

	public void openAccounts() {
		controller.setRequest(new OpenAccount(customers, account));
		controller.executeRequest();
	}

	public void closeAccount() {
		controller.setRequest(new CloseAccount(account));
		controller.executeRequest();
	}

}
