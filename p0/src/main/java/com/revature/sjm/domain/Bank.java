package com.revature.sjm.domain;

import com.revature.sjm.controller.BankRequestController;

/**
 *
 * @author Steve McQueen
 */
public class Bank {

	private int bankId;
	private String name;
	private BankRequestController bankRequestController;

	public Bank(int bankId, String name, BankRequestController bankRequestController) {
		this.bankId = bankId;
		this.name = name;
		this.bankRequestController = bankRequestController;
	}

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BankRequestController getBankRequestController() {
		return bankRequestController;
	}

	public void setBankRequestController(BankRequestController bankRequestController) {
		this.bankRequestController = bankRequestController;
	}
	
}
