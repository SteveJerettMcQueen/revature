package com.revature.sjm.domain;

import java.math.BigDecimal;

public class BankRequirement {

	public static final BigDecimal MAX_DEPOSIT = new BigDecimal(10000);
	public static final BigDecimal MIN_INIT_DEPOSIT = new BigDecimal(200);
	public static final BigDecimal MIN_BALANCE = new BigDecimal(100);
	public static final BigDecimal MAX_WITHDRAWAL = new BigDecimal(10000);

	private BankRequirement() {
	}

}
