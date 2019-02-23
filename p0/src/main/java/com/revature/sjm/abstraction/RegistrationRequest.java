package com.revature.sjm.abstraction;

import com.revature.sjm.service.CustomerManager;

public abstract class RegistrationRequest extends AbstractRequest {

	private CustomerManager customerManager;

	public RegistrationRequest() {
		customerManager = new CustomerManager();
	}

	public CustomerManager getCustomerManager() {
		return customerManager;
	}

}
