package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import abstraction.ApplicationController;
import dao.ExpenseReimbursementDAO;
import dao.ExpenseReimbursementStatusDAO;
import dao.ExpenseReimbursementTypeDAO;
import dao.ExpenseReimbursementUserDAO;
import dao.UserSubmittedExpenseReimbursementDAO;
import dto.ExpenseReimbursementDTO;
import dto.UserSubmittedExpenseReimbursementDTO;
import entity.ExpenseReimbursement;
import entity.UserSubmittedExpenseReimbursement;
import service.ExpenseReimbursementService;
import service.UserSubmittedExpenseReimbursementService;

public class ExpenseReimbursementController extends ApplicationController {

	private ExpenseReimbursementService expReimService;
	private UserSubmittedExpenseReimbursementService userSubExpReimService;

	@Override
	public void handleGet(HttpServletRequest request, HttpServletResponse response) {
		ExpenseReimbursementDAO reimDAO = new ExpenseReimbursementDAO();
		ExpenseReimbursementUserDAO userDAO = new ExpenseReimbursementUserDAO();
		ExpenseReimbursementTypeDAO typeDAO = new ExpenseReimbursementTypeDAO();
		ExpenseReimbursementStatusDAO statDAO = new ExpenseReimbursementStatusDAO();
		expReimService = new ExpenseReimbursementService(reimDAO, userDAO, typeDAO, statDAO);
		try {
			ObjectMapper om = new ObjectMapper();
			int id = Integer.valueOf(request.getParameter("id"));
			List<ExpenseReimbursement> reims = expReimService.findByUser(id);
			if (!reims.isEmpty()) {
				response.setContentType("application/json");
				response.getWriter().write(om.writeValueAsString(reims));
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
		ExpenseReimbursementDAO reimDAO = new ExpenseReimbursementDAO();
		ExpenseReimbursementUserDAO userDAO = new ExpenseReimbursementUserDAO();
		ExpenseReimbursementTypeDAO typeDAO = new ExpenseReimbursementTypeDAO();
		ExpenseReimbursementStatusDAO statDAO = new ExpenseReimbursementStatusDAO();
		UserSubmittedExpenseReimbursementDAO userSubExpReimDAO = new UserSubmittedExpenseReimbursementDAO();
		expReimService = new ExpenseReimbursementService(reimDAO, userDAO, typeDAO, statDAO);
		userSubExpReimService = new UserSubmittedExpenseReimbursementService(userDAO, reimDAO, userSubExpReimDAO);
		try {
			ObjectMapper om = new ObjectMapper();
			InputStream stream = request.getInputStream();
			ExpenseReimbursementDTO expReimDTO = om.readValue(stream, ExpenseReimbursementDTO.class);
			ExpenseReimbursement reim = expReimService.create(expReimDTO);
			UserSubmittedExpenseReimbursementDTO userSubExpReimDTO = new UserSubmittedExpenseReimbursementDTO();
			userSubExpReimDTO.setSubmitter(expReimDTO.getEruid());
			userSubExpReimDTO.setErid(reim.getErid());
			System.out.println(reim.getErid() + " : " + userSubExpReimDTO);
			UserSubmittedExpenseReimbursement userSubExpReim = userSubExpReimService.create(userSubExpReimDTO);
			if (userSubExpReim != null) {
				response.setContentType("application/json");
				response.getWriter().write(om.writeValueAsString(userSubExpReim));
			} else {
				response.setContentType("text/plain");
				response.getWriter().write("false");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void handlePut(HttpServletRequest request, HttpServletResponse response) {
		ExpenseReimbursementDAO reimDAO = new ExpenseReimbursementDAO();
		ExpenseReimbursementUserDAO userDAO = new ExpenseReimbursementUserDAO();
		ExpenseReimbursementTypeDAO typeDAO = new ExpenseReimbursementTypeDAO();
		ExpenseReimbursementStatusDAO statDAO = new ExpenseReimbursementStatusDAO();
		expReimService = new ExpenseReimbursementService(reimDAO, userDAO, typeDAO, statDAO);
		try {
			ObjectMapper om = new ObjectMapper();
			InputStream stream = request.getInputStream();
			ExpenseReimbursementDTO expReimDTO = om.readValue(stream, ExpenseReimbursementDTO.class);
			ExpenseReimbursement reim = expReimService.update(expReimDTO);
			System.out.println(expReimDTO);
			if (reim != null) {
				response.setContentType("application/json");
				response.getWriter().write(om.writeValueAsString(reim));
			} else {
				response.setContentType("text/plain");
				response.getWriter().write("false");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
