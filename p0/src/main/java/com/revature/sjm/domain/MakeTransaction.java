package com.revature.sjm.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.revature.sjm.BankApp;
import com.revature.sjm.abstraction.TransactionRequest;
import com.revature.sjm.service.AccountManager;
import com.revature.sjm.service.TransactionManager;

public class MakeTransaction extends TransactionRequest {

	private Customer customer;
	private Transaction transaction;

	public MakeTransaction(Customer customer, Transaction transaction) {
		this.customer = customer;
		this.transaction = transaction;
	}

	@Override
	public void require() {
		transaction.transact();

		Account account = transaction.getAccount();
		int acntId = account.getAcntId();
		int custId = customer.getCustId();
		int bankId = BankApp.bank.getBankId();
		int transId;
		Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

		Transaction newTrans = insertTransaction(transaction);
		transId = newTrans.getTransId();
		insertAccountTransaction(acntId, transId, timestamp);
		insertCustomerTransactionWithBank(custId, bankId, transId, timestamp);
		updateAccountBalance(account);

		/*
		 * Target account of transfer instance needs to be updated if transaction is an
		 * instance of 'Transfer'
		 * 
		 */
		if (transaction instanceof Transfer) {
			Transfer transfer = (Transfer) transaction;
			account = transfer.getTargetAccount();
			acntId = account.getAcntId();

			//Net transfer
			transfer.setAmount(transfer.getAmount().negate());
			newTrans = insertTransaction(transfer);
			transId = newTrans.getTransId();
			insertAccountTransaction(acntId, transId, timestamp);
			insertCustomerTransactionWithBank(custId, bankId, transId, timestamp);
			updateAccountBalance(account);
		}

		System.out.println("Created: " + newTrans);

	}

	private Transaction insertTransaction(Transaction transaction) {
		TransactionManager manager = super.getTransactionManager();
		return manager.insert(transaction);
	}

	private void insertAccountTransaction(int acntId, int transId, Timestamp timestamp) {
		TransactionManager manager = super.getTransactionManager();
		manager.insertAccountTransaction(acntId, transId, timestamp);
	}

	private void insertCustomerTransactionWithBank(int custId, int bankId, int transId, Timestamp timestamp) {
		TransactionManager manager = super.getTransactionManager();
		manager.insertCustomerTransactionWithBank(custId, bankId, transId, timestamp);
	}

	private void updateAccountBalance(Account account) {
		AccountManager manager = super.getAccountManager();
		manager.updateAccountBalance(account);
	}

}
