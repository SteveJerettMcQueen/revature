package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import entity.ExpenseReimbursementSystemAccess;
import entity.ExpenseReimbursementSystemAccessPK;
import util.DBUtil;

public class ExpenseReimbursementSystemAccessDAO {

	private static Logger LOGGER = Logger.getRootLogger();

	public static void main(String[] args) {
		ExpenseReimbursementSystemAccessDAO dao = new ExpenseReimbursementSystemAccessDAO();
		System.out.println(dao.findByUser(1));
	}

	public ExpenseReimbursementSystemAccess findByUser(int id) {
		String key = "findExpenseReimbursementSystemAccessById";
		String query = DBUtil.getQueryProperties().getProperty(key);
		Connection conn = DBUtil.getPostgresDataSource();
		ExpenseReimbursementSystemAccess ersa = null;
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				ExpenseReimbursementSystemAccessPK pkId = new ExpenseReimbursementSystemAccessPK();
				pkId.setUsername(rs.getString("username"));
				pkId.setPassword(rs.getString("password"));
				ersa = new ExpenseReimbursementSystemAccess();
				ersa.setErsaid(pkId);
			}
			return ersa;
		} catch (SQLException ex) {
			LOGGER.error(ex.toString());
		}
		return ersa;
	}

	public String findUserSaltForPasswordCheckBy(String username) {
		String key = "findUserSaltForPasswordCheckByUsername";
		String query = DBUtil.getQueryProperties().getProperty(key);
		Connection conn = DBUtil.getPostgresDataSource();
		String salt = null;
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				salt = rs.getString("salt");
			}
			return salt;
		} catch (SQLException ex) {
			LOGGER.error(ex.toString());
		}
		return salt;
	}

}
