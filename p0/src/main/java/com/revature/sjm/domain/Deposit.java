package com.revature.sjm.domain;

import java.math.BigDecimal;

/**
 *
 * @author Steve McQueen
 */
public class Deposit extends Transaction {

	public Deposit(String description, EntryForm form, BigDecimal amount, Account account) {
		super(description, form, amount, account);
	}

	@Override
	public void transact() {
		EntryForm form = super.getForm();
		CreditEntry creditEntry = new CreditEntry(form);
		BigDecimal amount = super.getAmount();
		Account account = super.getAccount();
		account.setEntry(creditEntry);
		account.executeEntry(amount);
	}
}
