package service;

import java.util.List;

import dao.ExpenseReimbursementStatusDAO;
import entity.ExpenseReimbursementStatus;

public class ExpenseReimbursementStatusService {

	private ExpenseReimbursementStatusDAO dao;

	public ExpenseReimbursementStatusService(ExpenseReimbursementStatusDAO dao) {
		this.dao = dao;
	}

	public List<ExpenseReimbursementStatus> findAll() {
		return dao.findAll();
	}

}
