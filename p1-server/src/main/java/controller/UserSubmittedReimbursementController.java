package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import abstraction.ApplicationController;
import dao.ExpenseReimbursementDAO;
import dao.ExpenseReimbursementUserDAO;
import dao.UserSubmittedExpenseReimbursementDAO;
import entity.UserSubmittedExpenseReimbursement;
import service.UserSubmittedExpenseReimbursementService;

public class UserSubmittedReimbursementController extends ApplicationController {

	private UserSubmittedExpenseReimbursementService service;

	@Override
	public void handleGet(HttpServletRequest request, HttpServletResponse response) {
		ExpenseReimbursementUserDAO userDAO = new ExpenseReimbursementUserDAO();
		ExpenseReimbursementDAO reimDAO = new ExpenseReimbursementDAO();
		UserSubmittedExpenseReimbursementDAO userSubExpReimDAO = new UserSubmittedExpenseReimbursementDAO();
		service = new UserSubmittedExpenseReimbursementService(userDAO, reimDAO, userSubExpReimDAO);
		try {
			ObjectMapper om = new ObjectMapper();
			List<UserSubmittedExpenseReimbursement> userSubExpReims = service.findAll();
			if (!userSubExpReims.isEmpty()) {
				response.setContentType("application/json");
				response.getWriter().write(om.writeValueAsString(userSubExpReims));
			} else {
				response.setContentType("text/plain");
				response.getWriter().write("false");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handlePost(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-genrated method stub

	}

	@Override
	public void handlePut(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	}
}
