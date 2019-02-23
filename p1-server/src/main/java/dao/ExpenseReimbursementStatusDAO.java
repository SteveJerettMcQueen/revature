package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import entity.ExpenseReimbursementStatus;
import util.DBUtil;

public class ExpenseReimbursementStatusDAO {

	private static Logger LOGGER = Logger.getRootLogger();

	public static void main(String[] args) {
		ExpenseReimbursementStatusDAO dao = new ExpenseReimbursementStatusDAO();
		System.out.println(dao.findByExpenseReimbursement(1));
	}

	public List<ExpenseReimbursementStatus> findAll() {
		String key = "findAllStatuses";
		String query = DBUtil.getQueryProperties().getProperty(key);
		Connection conn = DBUtil.getPostgresDataSource();
		List<ExpenseReimbursementStatus> statuses = null;
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			statuses = new ArrayList<>();
			while (rs.next()) {
				ExpenseReimbursementStatus status = new ExpenseReimbursementStatus();
				status.setStatusid(rs.getInt("statusid"));
				status.setStatus(rs.getString("status"));
				statuses.add(status);
			}
			return statuses;
		} catch (SQLException ex) {
			LOGGER.error(ex.toString());
		}
		return statuses;
	}

	public ExpenseReimbursementStatus findByExpenseReimbursement(int id) {
		String key = "findExpenseReimbursementStatusById";
		String query = DBUtil.getQueryProperties().getProperty(key);
		Connection conn = DBUtil.getPostgresDataSource();
		ExpenseReimbursementStatus status = null;
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				status = new ExpenseReimbursementStatus();
				status.setStatusid(rs.getInt("statusid"));
				status.setStatus(rs.getString("status"));
			}
			return status;
		} catch (SQLException ex) {
			LOGGER.error(ex.toString());
		}
		return status;
	}

}
