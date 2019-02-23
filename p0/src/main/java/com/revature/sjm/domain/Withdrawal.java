package com.revature.sjm.domain;

import java.math.BigDecimal;

/**
 *
 * @author Steve McQueen
 */
public class Withdrawal extends Transaction {

	public Withdrawal(String description, EntryForm form, BigDecimal amount, Account account) {
		super(description, form, amount, account);
	}

	@Override
	public void transact() {
		EntryForm form = super.getForm();
		DebitEntry debitEntry = new DebitEntry(form);
		BigDecimal amount = super.getAmount();
		Account account = super.getAccount();
		account.setEntry(debitEntry);
		account.executeEntry(amount);
		this.setAmount(amount.negate());
	}

}
