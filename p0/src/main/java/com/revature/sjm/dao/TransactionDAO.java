package com.revature.sjm.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.revature.sjm.domain.EntryForm;
import com.revature.sjm.domain.Transaction;
import com.revature.sjm.util.DBUtil;

public class TransactionDAO {

	private static final Connection CONN = DBUtil.getConnection();
	private static final Properties PROP = DBUtil.getProperties();

	public List<Transaction> findCustomerTransactions(int custId, int bankId, int acntId) {
		String query = PROP.getProperty("findCustomerTransactions.sql");
		List<Transaction> transactions = new ArrayList<>();
		try {
			PreparedStatement statement = CONN.prepareStatement(query);
			statement.setInt(1, custId);
			statement.setInt(2, bankId);
			statement.setInt(3, acntId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int transId = rs.getInt("transid");
				String description = rs.getString("description");
				EntryForm form = EntryForm.valueOf(rs.getString("form"));
				double amount = rs.getDouble("amount");
				Transaction trans = new Transaction(description, form, new BigDecimal(amount), null);
				trans.setTransId(transId);
				transactions.add(trans);
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return transactions;
	}

	public Transaction insert(Transaction transaction) {
		String query = PROP.getProperty("insertTransaction.sql");
		try {
			PreparedStatement statement = CONN.prepareStatement(query);
			statement.setString(1, transaction.getDescription());
			statement.setString(2, transaction.getForm().toString());
			statement.setDouble(3, transaction.getAmount().doubleValue());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				transaction.setTransId(resultSet.getInt("transId"));
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return transaction;
	}

	public void insertAccountTransaction(int acntId, int transId, Timestamp timestamp) {
		String query = PROP.getProperty("insertAccountTransaction.sql");
		try {
			PreparedStatement statement = CONN.prepareStatement(query);
			statement.setInt(1, acntId);
			statement.setInt(2, transId);
			statement.setTimestamp(3, timestamp);
			statement.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	public void insertCustomerTransactionWithBank(int custId, int bankId, int transId, Timestamp timestamp) {
		String query = PROP.getProperty("insertCustomerTransactionWithBank.sql");
		try {
			PreparedStatement statement = CONN.prepareStatement(query);
			statement.setInt(1, custId);
			statement.setInt(2, bankId);
			statement.setInt(3, transId);
			statement.setTimestamp(4, timestamp);
			statement.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

}
