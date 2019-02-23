package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import entity.ExpenseReimbursementSystemRole;
import util.DBUtil;

public class ExpenseReimbursementSystemRoleDAO {

	private static Logger LOGGER = Logger.getRootLogger();

	public static void main(String[] args) {
		ExpenseReimbursementSystemRoleDAO dao = new ExpenseReimbursementSystemRoleDAO();
		System.out.println(dao.findByUser(1));
	}

	public ExpenseReimbursementSystemRole findByUser(int id) {
		String key = "findUserRoleWithExpenseReimbursementSystemById";
		String query = DBUtil.getQueryProperties().getProperty(key);
		Connection conn = DBUtil.getPostgresDataSource();
		ExpenseReimbursementSystemRole role = null;
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				role = new ExpenseReimbursementSystemRole();
				role.setRoleid(rs.getInt("roleid"));
				role.setRole(rs.getString("role"));
			}
			return role;
		} catch (SQLException ex) {
			LOGGER.error(ex.toString());
		}
		return role;
	}

}
