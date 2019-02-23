package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import entity.UserSubmittedExpenseReimbursement;
import entity.UserSubmittedExpenseReimbursementPK;
import util.DBUtil;

public class UserSubmittedExpenseReimbursementDAO {

	private static Logger LOGGER = Logger.getRootLogger();

	public static void main(String[] args) {
		UserSubmittedExpenseReimbursementDAO dao = new UserSubmittedExpenseReimbursementDAO();
		System.out.println(dao.findByUser(2));
		System.out.println(dao.findAll());
	}

	public List<UserSubmittedExpenseReimbursement> findByUser(int id) {
		String key = "findUserSubmittedExpenseReimbursementById";
		String query = DBUtil.getQueryProperties().getProperty(key);
		Connection conn = DBUtil.getPostgresDataSource();
		List<UserSubmittedExpenseReimbursement> submissions = null;
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			submissions = new ArrayList<>();
			while (rs.next()) {
				UserSubmittedExpenseReimbursementPK pkId = new UserSubmittedExpenseReimbursementPK();
				pkId.setSubmitter(rs.getInt("submitter"));
				pkId.setErid(rs.getInt("erid"));
				UserSubmittedExpenseReimbursement submission = new UserSubmittedExpenseReimbursement();
				submission.setUserid(pkId);
				submission.setSubmitted(rs.getTimestamp("submitted"));
				submissions.add(submission);
			}
			return submissions;
		} catch (SQLException ex) {
			LOGGER.error(ex.toString());
		}
		return submissions;
	}

	public List<UserSubmittedExpenseReimbursement> findAll() {
		String key = "findAllUserSubmittedExpenseReimbursement";
		String query = DBUtil.getQueryProperties().getProperty(key);
		Connection conn = DBUtil.getPostgresDataSource();
		List<UserSubmittedExpenseReimbursement> submissions = null;
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			submissions = new ArrayList<>();
			while (rs.next()) {
				UserSubmittedExpenseReimbursementPK pkId = new UserSubmittedExpenseReimbursementPK();
				pkId.setSubmitter(rs.getInt("submitter"));
				pkId.setErid(rs.getInt("erid"));
				UserSubmittedExpenseReimbursement submission = new UserSubmittedExpenseReimbursement();
				submission.setUserid(pkId);
				submission.setSubmitted(rs.getTimestamp("submitted"));
				submissions.add(submission);
			}
			return submissions;
		} catch (SQLException ex) {
			LOGGER.error(ex.toString());
		}
		return submissions;
	}

	public UserSubmittedExpenseReimbursement create(UserSubmittedExpenseReimbursement userSubExpReim) {
		System.out.println(userSubExpReim);
		String key = "createUserSubmittedExpenseReimbursement";
		String query = DBUtil.getQueryProperties().getProperty(key);
		Connection conn = DBUtil.getPostgresDataSource();
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, userSubExpReim.getUserid().getSubmitter());
			statement.setInt(2, userSubExpReim.getUserid().getErid());
			statement.setTimestamp(3, userSubExpReim.getSubmitted());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				UserSubmittedExpenseReimbursementPK pkId = new UserSubmittedExpenseReimbursementPK();
				pkId.setSubmitter(resultSet.getInt("submitter"));
				pkId.setErid(resultSet.getInt("erid"));
				userSubExpReim.setUserid(pkId);
				userSubExpReim.setSubmitted((resultSet.getTimestamp("submitted")));
			}
			return userSubExpReim;
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return userSubExpReim;
	}

}
