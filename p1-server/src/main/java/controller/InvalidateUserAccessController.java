package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import abstraction.ApplicationController;

public class InvalidateUserAccessController extends ApplicationController {

	@Override
	public void handleGet(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		try {
			response.setContentType("text/plain");
			response.getWriter().write("true");
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
