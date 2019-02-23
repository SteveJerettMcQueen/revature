package com.revature.sjm.domain;

import java.math.BigDecimal;

import com.revature.sjm.abstraction.Transactionable;

/**
 *
 * @author Steve McQueen
 */
public class Transaction implements Transactionable {

	private int transId;
	private String description;
	private EntryForm form;
	private BigDecimal amount;
	private Account account;

	public Transaction(String description, EntryForm form, BigDecimal amount, Account account) {
		this.description = description;
		this.form = form;
		this.amount = amount;
		this.account = account;
	}

	@Override
	public void transact() {
		/*
		 * Method doesn't provide any default behavior. This method is abstract and
		 * should implementation should be delegated to its subclasses
		 * 
		 */
	}

	public int getTransId() {
		return transId;
	}

	public void setTransId(int transId) {
		this.transId = transId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public EntryForm getForm() {
		return form;
	}

	public void setForm(EntryForm form) {
		this.form = form;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Transaction [transId=" + transId + ", description=" + description + ", form=" + form + ", amount="
				+ amount + ", account=" + account + "]";
	}

}
