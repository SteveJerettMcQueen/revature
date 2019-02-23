package com.revature.sjm.domain;

import java.math.BigDecimal;

public class Transfer extends Transaction {

	private Account targetAccount;

	public Transfer(String description, EntryForm form, BigDecimal amount, Account account, Account targetAccount) {
		super(description, form, amount, account);
		this.targetAccount = targetAccount;
	}

	@Override
	public void transact() {
		EntryForm form = super.getForm();
		DebitEntry debitEntry = new DebitEntry(form);
		CreditEntry creditEntry = new CreditEntry(form);
		BigDecimal amount = super.getAmount();
		Account account = super.getAccount();
		account.setEntry(debitEntry);
		account.executeEntry(amount);
		targetAccount.setEntry(creditEntry);
		targetAccount.executeEntry(amount);
	}

	public Account getTargetAccount() {
		return targetAccount;
	}

	public void setTargetAccount(Account targetAccount) {
		this.targetAccount = targetAccount;
	}

}
