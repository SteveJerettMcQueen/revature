package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import abstraction.ApplicationController;
import dao.ExpenseReimbursementSystemAccessDAO;
import dao.ExpenseReimbursementSystemRoleDAO;
import dao.ExpenseReimbursementUserDAO;
import entity.ExpenseReimbursementUser;
import service.ExpenseReimbursementUserService;

public class ExpenseReimbursementUserController extends ApplicationController {

	private ExpenseReimbursementUserService service;

	@Override
	public void handleGet(HttpServletRequest request, HttpServletResponse response) {
		ExpenseReimbursementUserDAO userDAO = new ExpenseReimbursementUserDAO();
		ExpenseReimbursementSystemRoleDAO sysRoleDAO = new ExpenseReimbursementSystemRoleDAO();
		ExpenseReimbursementSystemAccessDAO sysAccDAO = new ExpenseReimbursementSystemAccessDAO();
		service = new ExpenseReimbursementUserService(userDAO, sysRoleDAO, sysAccDAO);
		try {
			ObjectMapper om = new ObjectMapper();
			List<ExpenseReimbursementUser> users = service.findAll();
			if (!users.isEmpty()) {
				response.setContentType("application/json");
				response.getWriter().write(om.writeValueAsString(users));
			} else {
				response.setContentType("text/plain");
				response.getWriter().write("No Users!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handlePost(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Handle Post!");
	}

	@Override
	public void handlePut(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}
}
