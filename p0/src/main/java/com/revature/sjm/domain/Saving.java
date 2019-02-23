package com.revature.sjm.domain;

import java.math.BigDecimal;

/**
 *
 * @author Steve McQueen
 */
public class Saving extends Account {

	public Saving(String name, BigDecimal balance, EntryForm form) {
        super(name, balance, form);
    }

}
