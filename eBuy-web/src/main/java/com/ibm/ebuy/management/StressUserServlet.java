package com.ibm.ebuy.management;

import java.io.IOException;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.ibm.ebuy.service.UserBean;

/**
 * Servlet implementation class GenerateUserServlet
 */
@WebServlet("/StressUserServlet")
public class StressUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private UserBean userBean;

	public StressUserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		requestHandler(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		requestHandler(request, response);
	}

	private void requestHandler(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (request.getParameter("add") != null) {
				for (int i = 1; i <= 1000; i++) {
					userBean.removeUser("user" + i);
					userBean.registerUser("user" + i, "puser" + i);
				}
			} else if (request.getParameter("remove") != null) {
				for (int i = 1; i <= 1000; i++) {
					userBean.removeUser("user" + i);
				}
			}

			request.getRequestDispatcher("/jsp/management.jsp").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
