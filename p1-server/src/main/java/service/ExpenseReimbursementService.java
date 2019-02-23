package service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import dao.ExpenseReimbursementDAO;
import dao.ExpenseReimbursementStatusDAO;
import dao.ExpenseReimbursementTypeDAO;
import dao.ExpenseReimbursementUserDAO;
import dto.ExpenseReimbursementDTO;
import entity.ExpenseReimbursement;
import entity.ExpenseReimbursementStatus;
import entity.ExpenseReimbursementType;
import entity.ExpenseReimbursementUser;

public class ExpenseReimbursementService {

	private ExpenseReimbursementDAO reimDAO;
	private ExpenseReimbursementUserDAO userDAO;
	private ExpenseReimbursementTypeDAO typeDAO;
	private ExpenseReimbursementStatusDAO statDAO;

	public ExpenseReimbursementService(ExpenseReimbursementDAO reimDAO, ExpenseReimbursementUserDAO userDAO,
			ExpenseReimbursementTypeDAO typeDAO, ExpenseReimbursementStatusDAO statDAO) {
		this.reimDAO = reimDAO;
		this.userDAO = userDAO;
		this.typeDAO = typeDAO;
		this.statDAO = statDAO;
	}

	public List<ExpenseReimbursement> findByUser(int id) {
		List<ExpenseReimbursement> reims = reimDAO.findByUser(id);
		for (int i = 0; i < reims.size(); i++) {
			ExpenseReimbursement reim = reims.get(i);
			int erid = reim.getErid();
			reim.setType(typeDAO.findByExpenseReimbursement(erid));
			reim.setStatus(statDAO.findByExpenseReimbursement(erid));
			reim.setResolver(userDAO.findWhoResolvedExpenseReimbursement(erid));
		}
		return reims;
	}

	public ExpenseReimbursement create(ExpenseReimbursementDTO dto) {
		ExpenseReimbursement reim = new ExpenseReimbursement();
		ExpenseReimbursementStatus status = new ExpenseReimbursementStatus();
		ExpenseReimbursementType type = new ExpenseReimbursementType();
		status.setStatusid(dto.getStatus().getStatusid());
		type.setTypeid(dto.getType().getTypeid());
		reim.setAmount(dto.getAmount());
		reim.setDescription(dto.getDescription());
		reim.setReceipt(dto.getReceipt());
		reim.setStatus(status);
		reim.setType(type);
		return reimDAO.create(reim);
	}

	public ExpenseReimbursement update(ExpenseReimbursementDTO expReimDTO) {
		ExpenseReimbursement reim = new ExpenseReimbursement();
		ExpenseReimbursementUser user = new ExpenseReimbursementUser();
		user.setEruid(expReimDTO.getResolver());
		reim.setAmount(expReimDTO.getAmount());
		reim.setDescription(expReimDTO.getDescription());
		reim.setErid(expReimDTO.getErid());
		reim.setReceipt(expReimDTO.getReceipt());
		reim.setResolved(Timestamp.valueOf(LocalDateTime.now()));
		reim.setResolver(user);
		reim.setStatus(expReimDTO.getStatus());
		reim.setType(expReimDTO.getType());
		System.out.println(reim);
		return reimDAO.update(reim);
	}

}
