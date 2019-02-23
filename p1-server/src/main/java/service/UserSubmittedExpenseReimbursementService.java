package service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import dao.ExpenseReimbursementDAO;
import dao.ExpenseReimbursementUserDAO;
import dao.UserSubmittedExpenseReimbursementDAO;
import dto.UserSubmittedExpenseReimbursementDTO;
import entity.UserSubmittedExpenseReimbursement;
import entity.UserSubmittedExpenseReimbursementPK;

public class UserSubmittedExpenseReimbursementService {

	private ExpenseReimbursementUserDAO userDAO;
	private ExpenseReimbursementDAO reimDAO;
	private UserSubmittedExpenseReimbursementDAO userSubExpReimDAO;

	public UserSubmittedExpenseReimbursementService(ExpenseReimbursementUserDAO userDAO,
			ExpenseReimbursementDAO reimDAO, UserSubmittedExpenseReimbursementDAO userSubExpReimDAO) {
		this.userDAO = userDAO;
		this.reimDAO = reimDAO;
		this.userSubExpReimDAO = userSubExpReimDAO;
	}

	public List<UserSubmittedExpenseReimbursement> findByUser(int id) {
		List<UserSubmittedExpenseReimbursement> userSubExpReims = userSubExpReimDAO.findByUser(id);
		for (int i = 0; i < userSubExpReims.size(); i++) {
			UserSubmittedExpenseReimbursement userSubExpReim = userSubExpReims.get(i);
			int erid = userSubExpReim.getUserid().getErid();
			userSubExpReim.setExpenseReimbursementUser(userDAO.findBy(id));
			userSubExpReim.setExpenseReimbursement(reimDAO.find(erid));
		}
		return userSubExpReims;
	}

	public List<UserSubmittedExpenseReimbursement> findAll() {
		List<UserSubmittedExpenseReimbursement> userSubExpReims = userSubExpReimDAO.findAll();
		for (int i = 0; i < userSubExpReims.size(); i++) {
			UserSubmittedExpenseReimbursement userSubExpReim = userSubExpReims.get(i);
			int submitter = userSubExpReim.getUserid().getSubmitter();
			int erid = userSubExpReim.getUserid().getErid();
			userSubExpReim.setExpenseReimbursementUser(userDAO.findBy(submitter));
			userSubExpReim.setExpenseReimbursement(reimDAO.find(erid));
		}
		return userSubExpReims;
	}

	public UserSubmittedExpenseReimbursement create(UserSubmittedExpenseReimbursementDTO dto) {
		UserSubmittedExpenseReimbursement userSubExpReim = new UserSubmittedExpenseReimbursement();
		UserSubmittedExpenseReimbursementPK pkId = new UserSubmittedExpenseReimbursementPK();
		pkId.setSubmitter(dto.getSubmitter());
		pkId.setErid(dto.getErid());
		userSubExpReim.setUserid(pkId);
		userSubExpReim.setSubmitted(Timestamp.valueOf(LocalDateTime.now()));
		return userSubExpReimDAO.create(userSubExpReim);
	}

}
