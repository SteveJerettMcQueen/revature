package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import abstraction.ApplicationController;
import controller.ExpenseReimbursementController;
import controller.ExpenseReimbursementStatusController;
import controller.ExpenseReimbursementTypeController;
import controller.ExpenseReimbursementUserController;
import controller.NullController;
import controller.UserAccessController;
import controller.UserSubmittedReimbursementController;
import util.ResourceControllerMapper;

public class DispatcherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static HashMap<String, ApplicationController> mapper = new HashMap<>();

	@Override
	public void init() {
		mapper.put(getContextParameter("loginURL"), new UserAccessController());
		mapper.put(getContextParameter("logoutURL"), new UserAccessController());
		mapper.put(getContextParameter("usersURL"), new ExpenseReimbursementUserController());
		mapper.put(getContextParameter("typesURL"), new ExpenseReimbursementTypeController());
		mapper.put(getContextParameter("statusesURL"), new ExpenseReimbursementStatusController());
		mapper.put(getContextParameter("userExpenseReimbursementsURL"), new ExpenseReimbursementController());
		mapper.put(getContextParameter("allExpenseReimbursementsURL"), new UserSubmittedReimbursementController());
		mapper.put(getContextParameter("expenseReimbursementURL"), new ExpenseReimbursementController());
	}

	protected String getContextParameter(String name) {
		return super.getServletContext().getInitParameter(name);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("controller", getApplicationController(request));
		super.service(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ApplicationController controller = (ApplicationController) request.getAttribute("controller");
		controller.handleGet(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ApplicationController controller = (ApplicationController) request.getAttribute("controller");
		controller.handlePost(request, response);
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ApplicationController controller = (ApplicationController) request.getAttribute("controller");
		controller.handlePut(request, response);
	}

	private ApplicationController getApplicationController(HttpServletRequest request) {
		String requestedResource = ResourceControllerMapper.getResource(request.getRequestURI());
		ArrayList<String> resources = new ArrayList<>(mapper.keySet());
		for (int i = 0; i < resources.size(); i++) {
			String resource = resources.get(i);
			if (requestedResource.equals(resource)) {
				return mapper.get(resource);
			}
		}
		return new NullController();
	}
}
