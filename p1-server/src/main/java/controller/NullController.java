package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import abstraction.ApplicationController;

public class NullController extends ApplicationController {

	@Override
	public void handleGet(HttpServletRequest request, HttpServletResponse response) {
		processRequest(request, response);
	}

	@Override
	public void handlePost(HttpServletRequest request, HttpServletResponse response) {
		processRequest(request, response);
	}

	@Override
	public void handlePut(HttpServletRequest request, HttpServletResponse response) {
		processRequest(request, response);
	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/plain");
			response.getWriter().write("false");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
