package com.revature.sjm.domain;

import java.math.BigDecimal;

import com.revature.sjm.abstraction.AbstractEntry;
import com.revature.sjm.exception.InvalidAmountException;

/**
 * 
 * @author Steve McQueen
 */
public class CreditEntry extends AbstractEntry {

	public CreditEntry(EntryForm form) {
		super(form);
	}

	@Override
	public void enter(BigDecimal amount) {
		BigDecimal absAmount = amount.abs();
		BigDecimal bal = getBalance();
		BigDecimal sum = bal.add(absAmount);
		BigDecimal MAX_DEPOSIT = BankRequirement.MAX_DEPOSIT;
		switch (absAmount.compareTo(BankRequirement.MAX_DEPOSIT)) {
		case 1:
			StringBuilder localMessage = new StringBuilder();
			localMessage.append("The amount entered is greater than the max deposit ").append(MAX_DEPOSIT).append("!");
			throw new InvalidAmountException(getBalance(), absAmount, localMessage.toString());
		case 0:
		case -1:
			setBalance(sum);
			break;
		}
	}

	@Override
	public BigDecimal getBalance() {
		return super.getBalance();
	}

	@Override
	public void setBalance(BigDecimal balance) {
		super.setBalance(balance);
	}

}
