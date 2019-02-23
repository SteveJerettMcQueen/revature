package com.revature.sjm.domain;

import java.math.BigDecimal;

import com.revature.sjm.abstraction.AbstractEntry;
import com.revature.sjm.exception.InvalidAmountException;

/**
 *
 * @author Steve McQueen
 */
public class DebitEntry extends AbstractEntry {

	public DebitEntry(EntryForm form) {
		super(form);
	}

	@Override
	public void enter(BigDecimal amount) {
		BigDecimal absAmount = amount.abs();
		BigDecimal bal = getBalance();
		BigDecimal diff = bal.subtract(absAmount);
		BigDecimal MIN_BALANCE = BankRequirement.MIN_BALANCE;
		switch (diff.compareTo(MIN_BALANCE)) {
		case 0:
			System.out.println("Log warning!");
		case 1:
			setBalance(diff);
			break;
		case -1:
			setBalance(MIN_BALANCE);
			throw new InvalidAmountException(diff, MIN_BALANCE, "The balance is below the required minium!");
		}
		super.setEnteredAmount(amount.negate());
	}

	@Override
	public BigDecimal getBalance() {
		return super.getBalance();
	}

	@Override
	public void setBalance(BigDecimal balance) {
		super.setBalance(balance);
	}

	@Override
	public BigDecimal getEnteredAmount() {
		return super.getEnteredAmount();
	}
}
