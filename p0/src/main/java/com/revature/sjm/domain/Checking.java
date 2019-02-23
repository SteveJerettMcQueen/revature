package com.revature.sjm.domain;

import java.math.BigDecimal;

/**
 *
 * @author Steve McQueen
 */
public class Checking extends Account {

	public Checking(String name, BigDecimal balance, EntryForm form) {
		super(name, balance, form);
	}

}
