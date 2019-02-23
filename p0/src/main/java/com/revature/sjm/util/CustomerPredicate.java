package com.revature.sjm.util;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.revature.sjm.domain.Customer;
import com.revature.sjm.exception.InvalidCustomerNameException;

/**
 *
 * @author Steve McQueen
 */
public class CustomerPredicate {

	public static Predicate<Customer> isValidName() {
		return new IsValidCustomerName();
	}

	public static Predicate<Customer> isValidSocialSecurityNumber() {
		return new IsValidSocialSecurityNumber();
	}
	
}

class IsValidCustomerName implements Predicate<Customer> {

	@Override
	public boolean test(Customer customer) {
		boolean isValidFirstName = isValidComplete(customer.getFirstName());
		boolean isValidLastName = isValidComplete(customer.getLastName());
		return (isValidFirstName && isValidLastName);
	}

	private boolean isValidComplete(String s) throws IllegalArgumentException {
		if (!(isValidLength(s) && isValidNameFormat(s))) {
			StringBuilder localMessage = new StringBuilder();
			localMessage.append("Input: ").append(s).append("is invalid! Length of name must be > 1 and < 21");
			throw new InvalidCustomerNameException(s, localMessage.toString());
		} else {
			return isValidLength(s) && isValidNameFormat(s);
		}
	}

	private boolean isValidLength(String s) {
		boolean isMinLength = s.length() > 1;
		boolean isMaxLength = s.length() < 21;
		boolean isInRange = (isMinLength && isMaxLength);
		return (s.isEmpty() || isInRange);
	}

	private boolean isValidNameFormat(String s) {
		Pattern pattern = Pattern.compile("[A-Z][a-zA-Z][^#&<>\\\"~;$^%{}?]{1,20}$");
		Matcher matcher = pattern.matcher(s);
		return matcher.find();
	}
}

class IsValidSocialSecurityNumber implements Predicate<Customer> {

	@Override
	public boolean test(Customer customer) {
		return isLengthNine(customer.getSocialSecurityNumber());
	}

	private boolean isLengthNine(int n) {
		return String.valueOf(n).length() == 9;
	}

}