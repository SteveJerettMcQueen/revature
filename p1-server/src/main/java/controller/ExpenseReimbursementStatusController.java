package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import abstraction.ApplicationController;
import dao.ExpenseReimbursementStatusDAO;
import entity.ExpenseReimbursementStatus;
import service.ExpenseReimbursementStatusService;

public class ExpenseReimbursementStatusController extends ApplicationController {

	private ExpenseReimbursementStatusService service;

	@Override
	public void handleGet(HttpServletRequest request, HttpServletResponse response) {
		ExpenseReimbursementStatusDAO statusDAO = new ExpenseReimbursementStatusDAO();
		service = new ExpenseReimbursementStatusService(statusDAO);
		try {
			ObjectMapper om = new ObjectMapper();
			List<ExpenseReimbursementStatus> statuses = service.findAll();
			if (!statuses.isEmpty()) {
				response.setContentType("application/json");
				response.getWriter().write(om.writeValueAsString(statuses));
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
		// TODO Auto-generated method stub

	}

	@Override
	public void handlePut(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

}
