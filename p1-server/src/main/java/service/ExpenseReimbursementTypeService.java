package service;

import java.util.List;

import dao.ExpenseReimbursementTypeDAO;
import entity.ExpenseReimbursementType;

public class ExpenseReimbursementTypeService {

	private ExpenseReimbursementTypeDAO dao;

	public ExpenseReimbursementTypeService(ExpenseReimbursementTypeDAO dao) {
		this.dao = dao;
	}

	public List<ExpenseReimbursementType> findAll() {
		return dao.findAll();
	}
}
