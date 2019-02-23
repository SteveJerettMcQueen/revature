package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import abstraction.ApplicationController;
import dao.ExpenseReimbursementTypeDAO;
import entity.ExpenseReimbursementType;
import service.ExpenseReimbursementTypeService;

public class ExpenseReimbursementTypeController extends ApplicationController {

	private ExpenseReimbursementTypeService service;

	@Override
	public void handleGet(HttpServletRequest request, HttpServletResponse response) {
		ExpenseReimbursementTypeDAO typeDAO = new ExpenseReimbursementTypeDAO();
		service = new ExpenseReimbursementTypeService(typeDAO);
		try {
			ObjectMapper om = new ObjectMapper();
			List<ExpenseReimbursementType> types = service.findAll();
			if (!types.isEmpty()) {
				response.setContentType("application/json");
				response.getWriter().write(om.writeValueAsString(types));
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
