package service;

import java.util.List;

import dao.ExpenseReimbursementSystemAccessDAO;
import dao.ExpenseReimbursementSystemRoleDAO;
import dao.ExpenseReimbursementUserDAO;
import dto.UserAccessDTO;
import entity.ExpenseReimbursementUser;
import util.PasswordUtil;

public class ExpenseReimbursementUserService {

	private ExpenseReimbursementUserDAO userDAO;
	private ExpenseReimbursementSystemRoleDAO sysRoleDAO;
	private ExpenseReimbursementSystemAccessDAO sysAccDAO;

	public ExpenseReimbursementUserService(ExpenseReimbursementUserDAO userDAO,
			ExpenseReimbursementSystemRoleDAO sysRoleDAO, ExpenseReimbursementSystemAccessDAO sysAccDAO) {
		this.userDAO = userDAO;
		this.sysRoleDAO = sysRoleDAO;
		this.sysAccDAO = sysAccDAO;
	}

	public ExpenseReimbursementUser determineAccess(UserAccessDTO dto) {
		String username = dto.getUsername();
		String password = dto.getPassword();
		String salt = sysAccDAO.findUserSaltForPasswordCheckBy(username);
		if (salt != null) {
			String hashedPassword = PasswordUtil.getHash(password, salt);
			int userId = userDAO.findExpenseReimbursementUserIdBy(username, hashedPassword);
			if (userId != 0) {
				return this.findBy(userId);
			}
		}
		return null;
	}

	public ExpenseReimbursementUser findBy(int id) {
		ExpenseReimbursementUser user = userDAO.findBy(id);
		user.setRole(sysRoleDAO.findByUser(id));
		user.setAccess(sysAccDAO.findByUser(id));
		return user;
	}

	public List<ExpenseReimbursementUser> findAll() {
		List<ExpenseReimbursementUser> users = userDAO.findAll();
		for (int i = 0; i < users.size(); i++) {
			ExpenseReimbursementUser user = users.get(i);
			int id = user.getEruid();
			user.setRole(sysRoleDAO.findByUser(id));
			user.setAccess(sysAccDAO.findByUser(id));
		}
		return users;
	}

}
