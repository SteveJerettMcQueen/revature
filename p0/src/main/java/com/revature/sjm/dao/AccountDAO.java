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

import com.revature.sjm.domain.Account;
import com.revature.sjm.util.DBUtil;

public class AccountDAO {

	private static final Connection CONN = DBUtil.getConnection();
	private static final Properties PROP = DBUtil.getProperties();

	public Account findAccountById(int id) {
		String query = PROP.getProperty("findAccountById.sql");
		Account account = null;
		try {
			PreparedStatement statement = CONN.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				String acntName = rs.getString("acntname");
				double balance = rs.getDouble("balance");
				account = new Account(acntName, new BigDecimal(balance));
				account.setAcntId(id);
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return account;
	}

	public List<Account> findCustomerAccountsById(int id) {
		String query = PROP.getProperty("findCustomerAccountsById.sql");
		List<Account> accounts = new ArrayList<>();
		try {
			PreparedStatement statement = CONN.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int acntId = rs.getInt("acntid");
				String acntName = rs.getString("acntname");
				double balance = rs.getDouble("balance");
				Account account = new Account(acntName, new BigDecimal(balance));
				account.setAcntId(acntId);
				accounts.add(account);
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return accounts;
	}

	public Account insert(Account account) {
		String query = PROP.getProperty("insertAccount.sql");
		try {
			PreparedStatement statement = CONN.prepareStatement(query);
			statement.setString(1, account.getName());
			statement.setDouble(2, account.getBalance().doubleValue());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				account.setAcntId(resultSet.getInt("acntId"));
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return account;
	}

	public void insertCustomerOwnedAccount(int acntId, int custId, Timestamp timestamp) {
		String query = PROP.getProperty("insertCustomerOwnedAccount.sql");
		try {
			PreparedStatement statement = CONN.prepareStatement(query);
			statement.setInt(1, acntId);
			statement.setInt(2, custId);
			statement.setTimestamp(3, timestamp);
			statement.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	public void updateAccountBalance(Account account) {
		String query = PROP.getProperty("updateAccountBalance.sql");
		try {
			PreparedStatement statement = CONN.prepareStatement(query);
			statement.setDouble(1, account.getBalance().doubleValue());
			statement.setInt(2, account.getAcntId());
			statement.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	public void remove(Account account) {
		String query = PROP.getProperty("removeAccount.sql");
		try {
			PreparedStatement statement = CONN.prepareStatement(query);
			statement.setInt(1, account.getAcntId());
			statement.execute();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}
}
