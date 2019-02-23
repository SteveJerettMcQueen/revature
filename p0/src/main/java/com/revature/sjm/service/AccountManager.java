package com.revature.sjm.service;

import java.sql.Timestamp;
import java.util.List;

import com.revature.sjm.dao.AccountDAO;
import com.revature.sjm.domain.Account;
import com.revature.sjm.domain.Customer;

/**
 *
 * @author Steve McQueen
 */
public class AccountManager {

	private static final AccountDAO acntDAO = new AccountDAO();

	public Account findAccountById(int id) {
		return acntDAO.findAccountById(id);
	}

	public List<Account> findCustomerAccountsById(int id) {
		return acntDAO.findCustomerAccountsById(id);
	}

	public Account insert(Account account) {
		return acntDAO.insert(account);
	}

	public void insertCustomerOwnedAccount(int acntId, int custId, Timestamp timestamp) {
		acntDAO.insertCustomerOwnedAccount(acntId, custId, timestamp);
	}

	public void updateAccountBalance(Account account) {
		acntDAO.updateAccountBalance(account);
	}

	public void remove(Account account) {
		acntDAO.remove(account);
	}
}
