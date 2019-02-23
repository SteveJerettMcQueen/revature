package com.revature.sjm.controller;

import com.revature.sjm.abstraction.AbstractRequest;
import com.revature.sjm.domain.NullRequest;

public class BankRequestController {

	private AbstractRequest request;

	public BankRequestController() {
		request = new NullRequest();

	}

	public void setRequest(AbstractRequest request) {
		this.request = request;
	}

	public void executeRequest() {
		request.require();
	}

}
