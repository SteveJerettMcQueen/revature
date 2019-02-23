package com.revature.sjm.domain;

import java.math.BigDecimal;

import com.revature.sjm.abstraction.AbstractEntry;

/**
 *
 * @author Steve McQueen
 */
public class NeutralEntry extends AbstractEntry {

	public NeutralEntry(EntryForm form) {
		super(form);
	}

	@Override
	public void enter(BigDecimal amount) {

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
