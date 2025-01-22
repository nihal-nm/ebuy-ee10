package com.ibm.ebuy.servlet;

import java.io.IOException;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.ibm.ebuy.service.interfaces.UserBeanLocal;

/**
 * Handle register request
 * 
 * @author QiaoXi
 * 
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	UserBeanLocal userBean;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		requestHandler(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		requestHandler(request, response);
	}

	private void requestHandler(HttpServletRequest request,
			HttpServletResponse response) {
		if (request.getParameter("register") != null) {
			try {
				String username = request.getParameter("username").trim();
				String password1 = request.getParameter("password1").trim();
				String password2 = request.getParameter("password2").trim();
				String message = null;
				// System.out.print(username + password1 + password2);
				if (username == null || username.length() < 1) {// No username
					message = "Please input your USERNAME";
				} else if (!password1.equals(password2)) {// password not match
					message = "Please confirm your PASSWORD";
				} else {
					int result = userBean.registerUser(username, password1);
					if (result == 0) {// Register succeed, jump to login.jsp
						message = "Congratulations! Register succeed, plase login";
						request.setAttribute("message", message);
						request.getRequestDispatcher("LoginServlet").forward(
								request, response);
						return;
					} else if (result == 1) {
						message = "USERNAME already exist, please use another one";
					} else {
						message = "Unknow error occur, please try again";
					}
				}
				request.setAttribute("message", message);
				request.getRequestDispatcher("/jsp/register.jsp").forward(
						request, response);
				return;
			} catch (Exception e) {// error occur
				e.printStackTrace();
				try {
					request.getRequestDispatcher("/jsp/register.jsp").forward(
							request, response);
					return;
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} else {
			try {
				request.getRequestDispatcher("/jsp/register.jsp").forward(
						request, response);
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
