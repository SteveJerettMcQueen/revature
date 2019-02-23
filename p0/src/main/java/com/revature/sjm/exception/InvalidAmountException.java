package com.revature.sjm.exception;

import java.math.BigDecimal;

/**
 *
 * @author Steve McQueen
 */
public class InvalidAmountException extends IllegalArgumentException {

    private final String localMessage;
    private final BigDecimal amount, balance;

    public InvalidAmountException(BigDecimal balance, BigDecimal amount, String localMessage) {
        this.balance = balance;
        this.amount = amount;
        this.localMessage = localMessage;
    }

    @Override
    public String toString() {
        return "InvalidAmounException{" + "localMessage=" + localMessage + ", amount=" + amount + ", balance=" + balance + '}';
    }

}
