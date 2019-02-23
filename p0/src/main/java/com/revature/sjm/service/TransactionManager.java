package com.revature.sjm.service;

import java.sql.Timestamp;
import java.util.List;

import com.revature.sjm.dao.TransactionDAO;
import com.revature.sjm.domain.Transaction;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Steve McQueen
 */
public class TransactionManager {

	private static final TransactionDAO transDAO = new TransactionDAO();

	public List<Transaction> findCustomerTransactions(int custId, int bankId, int acntId) {
		return transDAO.findCustomerTransactions(custId, bankId, acntId);
	}
	
	public Transaction insert(Transaction transaction) {
		return transDAO.insert(transaction);
	}

	public void insertAccountTransaction(int acntId, int transId, Timestamp timestamp) {
		transDAO.insertAccountTransaction(acntId, transId, timestamp);
	}

	public void insertCustomerTransactionWithBank(int custId, int bankId, int transId, Timestamp timestamp) {
		transDAO.insertCustomerTransactionWithBank(custId, bankId, transId, timestamp);
	}

}
