package com.revature.sjm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;

import com.revature.sjm.domain.Customer;
import com.revature.sjm.util.DBUtil;

public class CustomerDAO {

	private static final Connection CONN = DBUtil.getConnection();
	private static final Properties PROP = DBUtil.getProperties();

	public Customer findCustomerById(int id) {
		String query = PROP.getProperty("findCustomerById.sql");
		Customer customer = null;
		try {
			PreparedStatement statement = CONN.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				String firstName = rs.getString("firstName");
				String middleName = rs.getString("middleName");
				String lastName = rs.getString("lastName");
				int ssn = rs.getInt("ssn");
				customer = new Customer(firstName, middleName, lastName, ssn);
				customer.setCustId(id);
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return customer;
	}

	public String findCustomerPasswordByUserName(int bankId, String userName) {
		String query = PROP.getProperty("findCustomerPasswordByUserName.sql");
		String password = null;
		try {
			PreparedStatement statement = CONN.prepareStatement(query);
			statement.setInt(1, bankId);
			statement.setString(2, userName);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				password = rs.getString("password");
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return password;
	}

	public Customer insert(Customer customer) {
		String query = PROP.getProperty("insertCustomer.sql");
		try {
			PreparedStatement statement = CONN.prepareStatement(query);
			statement.setString(1, customer.getFirstName());
			statement.setString(2, customer.getMiddleName());
			statement.setString(3, customer.getLastName());
			statement.setInt(4, customer.getSocialSecurityNumber());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				customer.setCustId(resultSet.getInt("custId"));
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return customer;
	}

	public void insertBankRegisteredCustomer(int bankId, int custId, Timestamp timestamp) {
		String query = PROP.getProperty("insertBankRegisteredCustomer.sql");
		try {
			PreparedStatement statement = CONN.prepareStatement(query);
			statement.setInt(1, bankId);
			statement.setInt(2, custId);
			statement.setTimestamp(3, timestamp);
			statement.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	public void insertCustomerAccessWithBank(int custId, int bankId, String userName, String password, String salt) {
		String query = PROP.getProperty("insertCustomerAccessWithBank.sql");
		try {
			PreparedStatement statement = CONN.prepareStatement(query);
			statement.setInt(1, custId);
			statement.setInt(2, bankId);
			statement.setString(3, userName);
			statement.setString(4, password);
			statement.setString(5, salt);
			statement.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex);
		}

	}

	public Customer findCustomerByUserNameAndPassword(String userName, String password) {
		String query = PROP.getProperty("findCustomerByUserNameAndPassword.sql");
		Customer customer = null;
		try {
			PreparedStatement statement = CONN.prepareStatement(query);
			statement.setString(1, userName);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				int custId = rs.getInt("custid");
				String firstName = rs.getString("firstName");
				String middleName = rs.getString("middleName");
				String lastName = rs.getString("lastName");
				int ssn = rs.getInt("ssn");
				customer = new Customer(firstName, middleName, lastName, ssn);
				customer.setCustId(custId);
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return customer;
	}

	public String findCustomerSaltForPasswordCheck(String userName) {
		String query = PROP.getProperty("findCustomerSaltForPasswordCheck.sql");
		String salt = null;
		try {
			PreparedStatement statement = CONN.prepareStatement(query);
			statement.setString(1, userName);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				salt = rs.getString("salt");
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return salt;
	}

}
