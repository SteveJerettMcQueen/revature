package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import entity.ExpenseReimbursement;
import util.DBUtil;

public class ExpenseReimbursementDAO {

	private static Logger LOGGER = Logger.getRootLogger();

	public static void main(String[] args) {
		ExpenseReimbursementDAO dao = new ExpenseReimbursementDAO();
		System.out.println(dao.findByUser(2));
	}

	public ExpenseReimbursement find(int id) {
		String key = "findExpenseReimbursementById";
		String query = DBUtil.getQueryProperties().getProperty(key);
		Connection conn = DBUtil.getPostgresDataSource();
		ExpenseReimbursement reim = null;
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				reim = new ExpenseReimbursement();
				reim.setErid(id);
				reim.setDescription(rs.getString("description"));
				reim.setAmount(rs.getBigDecimal("amount"));
				reim.setResolved(rs.getTimestamp("resolved"));
				reim.setReceipt(rs.getBytes("receipt"));
			}
			return reim;
		} catch (SQLException ex) {
			LOGGER.error(ex.toString());
		}
		return reim;
	}

	public ExpenseReimbursement create(ExpenseReimbursement reim) {
		String key = "createExpenseReimbursement";
		String query = DBUtil.getQueryProperties().getProperty(key);
		Connection conn = DBUtil.getPostgresDataSource();
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, reim.getDescription());
			statement.setBigDecimal(2, reim.getAmount());
			statement.setBytes(3, reim.getReceipt());
			statement.setInt(4, reim.getType().getTypeid());
			statement.setInt(5, reim.getStatus().getStatusid());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				reim.setErid(resultSet.getInt("erid"));
			}
			return reim;
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return reim;
	}

	public ExpenseReimbursement findResolvedExpenseReimbursementByUser(int id) {
		String key = "findResolvedExpenseReimbursementByUserId";
		String query = DBUtil.getQueryProperties().getProperty(key);
		Connection conn = DBUtil.getPostgresDataSource();
		ExpenseReimbursement reim = null;
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				reim = new ExpenseReimbursement();
				reim.setErid(id);
				reim.setDescription(rs.getString("description"));
				reim.setAmount(rs.getBigDecimal("amount"));
				reim.setResolved(rs.getTimestamp("resolved"));
				reim.setReceipt(rs.getBytes("receipt"));
			}
			return reim;
		} catch (SQLException ex) {
			LOGGER.error(ex.toString());
		}
		return reim;
	}

	public List<ExpenseReimbursement> findByUser(int id) {
		String key = "findExpenseReimbursementsByUserId";
		String query = DBUtil.getQueryProperties().getProperty(key);
		Connection conn = DBUtil.getPostgresDataSource();
		List<ExpenseReimbursement> reimbursements = null;
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			reimbursements = new ArrayList<>();
			while (rs.next()) {
				ExpenseReimbursement reim = new ExpenseReimbursement();
				int erid = rs.getInt("erid");
				reim.setErid(erid);
				reim.setDescription(rs.getString("description"));
				reim.setAmount(rs.getBigDecimal("amount"));
				reim.setResolved(rs.getTimestamp("resolved"));
				reim.setReceipt(rs.getBytes("receipt"));
				reimbursements.add(reim);
			}
			return reimbursements;
		} catch (SQLException ex) {
			LOGGER.error(ex.toString());
		}
		return reimbursements;
	}

	public ExpenseReimbursement update(ExpenseReimbursement reim) {
		System.out.println("reim: " + reim);
		String key = "updateExpenseReimbursement";
		String query = DBUtil.getQueryProperties().getProperty(key);
		Connection conn = DBUtil.getPostgresDataSource();
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, reim.getDescription());
			statement.setBigDecimal(2, reim.getAmount());
			statement.setInt(3, reim.getType().getTypeid());
			statement.setInt(4, reim.getStatus().getStatusid());
			statement.setBytes(5, reim.getReceipt());
			statement.setInt(6, reim.getResolver().getEruid());
			statement.setTimestamp(7, reim.getResolved());
			statement.setInt(8, reim.getErid());
			statement.executeQuery();
			return reim;
		} catch (SQLException ex) {
			LOGGER.error(ex.toString());
		}
		return reim;
	}
}
