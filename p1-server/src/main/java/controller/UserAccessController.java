package controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import abstraction.ApplicationController;
import dao.ExpenseReimbursementSystemAccessDAO;
import dao.ExpenseReimbursementSystemRoleDAO;
import dao.ExpenseReimbursementUserDAO;
import dto.UserAccessDTO;
import entity.ExpenseReimbursementUser;
import service.ExpenseReimbursementUserService;

public class UserAccessController extends ApplicationController {

	private ExpenseReimbursementUserService service;

	/*
	 * Two possible ways to remember the user: 1. Tokens and database table mapping
	 * tokens to users 2. JWT - JSON Web Token
	 */

	@Override
	public void handleGet(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("User Access Get!");
	}

	@Override
	public void handlePost(HttpServletRequest request, HttpServletResponse response) {
		ExpenseReimbursementUserDAO userDAO = new ExpenseReimbursementUserDAO();
		ExpenseReimbursementSystemRoleDAO roleDAO = new ExpenseReimbursementSystemRoleDAO();
		ExpenseReimbursementSystemAccessDAO sysAccDAO = new ExpenseReimbursementSystemAccessDAO();
		service = new ExpenseReimbursementUserService(userDAO, roleDAO, sysAccDAO);
		try {
			ObjectMapper om = new ObjectMapper();
			InputStream stream = request.getInputStream();
			UserAccessDTO dto = om.readValue(stream, UserAccessDTO.class);
			ExpenseReimbursementUser user = service.determineAccess(dto);
			if (user != null) {
				response.setContentType("application/json");
				response.getWriter().write(om.writeValueAsString(user));
			} else {
				response.setContentType("text/plain");
				response.getWriter().write("false");
			}
		} catch (IOException e) {
			/*
			 * Send an error status
			 * 
			 */
		}
	}

	@Override
	public void handlePut(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

}
