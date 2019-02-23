package com.revature.sjm.util;

import java.util.function.Predicate;

import com.revature.sjm.domain.Customer;

/**
 *
 * @author Steve McQueen
 */
public class CustomerValidator {

	private Customer customer;

	public CustomerValidator(Customer customer) {
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public boolean validate() {
		return isAllComplete();
	}

	private boolean testPredicate(Predicate<Customer> predicate) {
		return predicate.test(customer);
	}

	private boolean isAllComplete() {
		boolean isAllComplete = (testPredicate(CustomerPredicate.isValidName())
				&& testPredicate(CustomerPredicate.isValidSocialSecurityNumber()));
		return isAllComplete;
	}

}
