package com.revature.sjm.domain;

import java.math.BigDecimal;

import com.revature.sjm.abstraction.AbstractEntry;

/**
 *
 * @author Steve McQueen
 */
public class Account {

	private int acntId;
	private String name;
	private BigDecimal balance;
	private AbstractEntry entry;

	public Account(String name, BigDecimal balance, EntryForm form) {
		this.name = name;
		this.setBalance(balance);
		this.entry = new NeutralEntry(form);
	}

	public Account(String name, BigDecimal balance) {
		this.name = name;
		this.setBalance(balance);
	}

	public int getAcntId() {
		return acntId;
	}

	public void setAcntId(int acntId) {
		this.acntId = acntId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance.abs();
	}

	public AbstractEntry getEntry() {
		return entry;
	}

	public void setEntry(AbstractEntry entry) {
		this.entry = entry;
	}

	public void executeEntry(BigDecimal amount) {
		entry.setBalance(balance);
		entry.enter(amount);
		setBalance(entry.getBalance());
	}

	@Override
	public String toString() {
		return "Account [acntId=" + acntId + ", name=" + name + ", balance=" + balance + "]";
	}

}
