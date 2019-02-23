package com.revature.sjm.abstraction;

import java.math.BigDecimal;

import com.revature.sjm.domain.EntryForm;

public abstract class AbstractEntry implements Enterable {

	private EntryForm form;
	private BigDecimal balance, enteredAmount;

	public AbstractEntry(EntryForm form) {
		this.form = form;
		this.balance = new BigDecimal(0);
		this.enteredAmount = new BigDecimal(0);
	}

	@Override
	public abstract void enter(BigDecimal amount);

	public EntryForm getForm() {
		return form;
	}

	public void setForm(EntryForm form) {
		this.form = form;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getEnteredAmount() {
		return enteredAmount;
	}

	public void setEnteredAmount(BigDecimal enteredAmount) {
		this.enteredAmount = enteredAmount;
	}

	@Override
	public String toString() {
		return "AbstractEntry [form=" + form + ", balance=" + balance + ", enteredAmount=" + enteredAmount + "]";
	}
}
