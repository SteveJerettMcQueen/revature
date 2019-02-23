package com.revature.sjm.view;

import com.revature.sjm.controller.BankRequestController;
import com.revature.sjm.domain.Customer;
import com.revature.sjm.domain.LogIn;
import com.revature.sjm.domain.Register;
import com.revature.sjm.util.CustomerValidator;

public class RegistrationRequestView {

	private CustomerValidator validator;
	private BankRequestController controller;

	public RegistrationRequestView(BankRequestController controller) {
		this.controller = controller;
	}

	public CustomerValidator getValidator() {
		return validator;
	}

	public void setValidator(CustomerValidator validator) {
		this.validator = validator;
	}

	public Customer logInCustomer(String userName, String password) {
		LogIn logIn = new LogIn(userName, password);
		controller.setRequest(logIn);
		controller.executeRequest();
		return logIn.getCustomer();
	}

	public Customer registerCustomer() {
		if (validator.validate()) {
			Register register = new Register(validator.getCustomer());
			controller.setRequest(register);
			controller.executeRequest();
			return register.getRegisteredCustomer();
		} else {
			System.out.println("Customer did not pass validation!");
		}
		return null;
	}

}
