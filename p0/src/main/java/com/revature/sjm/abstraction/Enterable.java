package com.revature.sjm.abstraction;

import java.math.BigDecimal;

/**
 *
 * @author Steve McQueen
 */
public interface Enterable {

    public void enter(BigDecimal amount) throws IllegalArgumentException;

}
