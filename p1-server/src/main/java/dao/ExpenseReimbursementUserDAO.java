package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import entity.ExpenseReimbursementUser;
import util.DBUtil;

public class ExpenseReimbursementUserDAO {

	private static Logger LOGGER = Logger.getRootLogger();

	public static void main(String[] args) {
		ExpenseReimbursementUserDAO dao = new ExpenseReimbursementUserDAO();
		System.out.println(dao.findAll());
	}

	public List<ExpenseReimbursementUser> findAll() {
		String key = "findAllExpenseReimbursementUsers";
		String query = DBUtil.getQueryProperties().getProperty(key);
		Connection conn = DBUtil.getPostgresDataSource();
		List<ExpenseReimbursementUser> users = null;
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			users = new ArrayList<>();
			while (rs.next()) {
				ExpenseReimbursementUser user = new ExpenseReimbursementUser();
				user.setEruid(rs.getInt("eruid"));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setEmail(rs.getString("email"));
				users.add(user);
			}
			return users;
		} catch (SQLException ex) {
			LOGGER.error(ex.toString());
		}
		return users;
	}

	public ExpenseReimbursementUser findBy(int id) {
		String key = "findExpenseReimbursementUserById";
		String query = DBUtil.getQueryProperties().getProperty(key);
		Connection conn = DBUtil.getPostgresDataSource();
		ExpenseReimbursementUser user = null;
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				user = new ExpenseReimbursementUser();
				user.setEruid(id);
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setEmail(rs.getString("email"));
			}
			return user;
		} catch (SQLException ex) {
			LOGGER.error(ex.toString());
		}
		return user;
	}

	public ExpenseReimbursementUser findWhoResolvedExpenseReimbursement(int id) {
		String key = "findWhoResolvedExpenseReimbursementById";
		String query = DBUtil.getQueryProperties().getProperty(key);
		Connection conn = DBUtil.getPostgresDataSource();
		ExpenseReimbursementUser user = null;
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				user = new ExpenseReimbursementUser();
				user.setEruid(rs.getInt("eruid"));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setEmail(rs.getString("email"));
			}
			return user;
		} catch (SQLException ex) {
			LOGGER.error(ex.toString());
		}
		return user;
	}

	public int findExpenseReimbursementUserIdBy(String username, String password) {
		String key = "findExpenseReimbursementUserIdByUsernamePassword";
		String query = DBUtil.getQueryProperties().getProperty(key);
		Connection conn = DBUtil.getPostgresDataSource();
		int id = 0;
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				id = rs.getInt("eruid");
			}
			return id;
		} catch (SQLException ex) {
			LOGGER.error(ex.toString());
		}
		return id;
	}

}
