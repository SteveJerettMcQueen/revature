package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import entity.ExpenseReimbursementType;
import util.DBUtil;

public class ExpenseReimbursementTypeDAO {

	private static Logger LOGGER = Logger.getRootLogger();

	public static void main(String[] args) {
		ExpenseReimbursementTypeDAO dao = new ExpenseReimbursementTypeDAO();
		System.out.println(dao.findByExpenseReimbursement(1));
	}

	public List<ExpenseReimbursementType> findAll() {
		String key = "findAllTypes";
		String query = DBUtil.getQueryProperties().getProperty(key);
		Connection conn = DBUtil.getPostgresDataSource();
		List<ExpenseReimbursementType> types = null;
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			types = new ArrayList<>();
			while (rs.next()) {
				ExpenseReimbursementType type = new ExpenseReimbursementType();
				type.setTypeid(rs.getInt("typeid"));
				type.setType(rs.getString("type"));
				types.add(type);
			}
			return types;
		} catch (SQLException ex) {
			LOGGER.error(ex.toString());
		}
		return types;
	}

	public ExpenseReimbursementType findByExpenseReimbursement(int id) {
		String key = "findExpenseReimbursementTypeById";
		String query = DBUtil.getQueryProperties().getProperty(key);
		Connection conn = DBUtil.getPostgresDataSource();
		ExpenseReimbursementType type = null;
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				type = new ExpenseReimbursementType();
				type.setTypeid(rs.getInt("typeid"));
				type.setType(rs.getString("type"));
			}
			return type;
		} catch (SQLException ex) {
			LOGGER.error(ex.toString());
		}
		return type;
	}

}
