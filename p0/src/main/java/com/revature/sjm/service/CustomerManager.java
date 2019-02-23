package com.revature.sjm.service;

import java.sql.Timestamp;
import java.util.List;

import com.revature.sjm.dao.CustomerDAO;
import com.revature.sjm.domain.Customer;
import com.revature.sjm.domain.Transaction;

/**
 * @author Steve McQueen
 */
public class CustomerManager {

	private static final CustomerDAO custDAO = new CustomerDAO();

	public Customer findCustomerById(int id) {
		return custDAO.findCustomerById(id);
	}

	public Customer findCustomerByUserNameAndPassword(String userName, String password) {
		return custDAO.findCustomerByUserNameAndPassword(userName, password);
	}

	public String findCustomerPasswordByUserName(int bankid, String userName) {
		return custDAO.findCustomerPasswordByUserName(bankid, userName);
	}

	public Customer insert(Customer customer) {
		return custDAO.insert(customer);
	}

	public void insertBankRegisteredCustomer(int bankId, int custId, Timestamp timestamp) {
		custDAO.insertBankRegisteredCustomer(bankId, custId, timestamp);
	}

	public void insertCustomerAccessWithBank(int custId, int bankId, String userName, String password, String salt) {
		custDAO.insertCustomerAccessWithBank(custId, bankId, userName, password, salt);
	}

	public String findCustomerSaltForPasswordCheck(String userName) {
		return custDAO.findCustomerSaltForPasswordCheck(userName);
	}

}
