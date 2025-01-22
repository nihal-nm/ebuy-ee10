package com.ibm.ebuy.servlet;

import java.io.IOException;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.ibm.ebuy.entity.User;
import com.ibm.ebuy.pojo.SessionInfo;
import com.ibm.ebuy.service.interfaces.UserBeanLocal;

/**
 * Servlet to handle Login request
 * 
 * @author QiaoXi
 * 
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	UserBeanLocal userBean;

	public LoginServlet() {
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
			if (request.getParameter("login") != null) {// user login
				String username = request.getParameter("username").trim();
				String password = request.getParameter("password").trim();
				String message = "Unknow error occur, please try again";
				if (username.length() < 1) {
					message = "Please input your USERNAME";
				} else {
					User user = userBean.checkUser(username, password);
					if (user == null) {// Login fail
						message = "Login fail, please check your USERNAME and PASSWORD";
					} else {// Login success
						// Create and fill session info
						HttpSession session = request.getSession(true);
						SessionInfo sessionInfo = (SessionInfo) session
								.getAttribute("sessionInfo");
						if (sessionInfo == null) {
							sessionInfo = new SessionInfo(username);
						} else {
							sessionInfo.setUsername(username);
						}		
						session.setAttribute("sessionInfo", sessionInfo);
						request.getRequestDispatcher("BookServlet").forward(request,
								response);
						return;
					}
				}
				request.setAttribute("message", message);
				request.getRequestDispatcher("/jsp/login.jsp").forward(request,
						response);
				return;
			} else if (request.getParameter("logout") != null) {// user logout
				HttpSession session = request.getSession();
				session.invalidate();//delete all session info
//				if (session != null) {
//					SessionInfo sessionInfo = (SessionInfo) session
//							.getAttribute("sessionInfo");
//					if (sessionInfo != null) {
//						sessionInfo.setUsername(null);
//					}
//				}
				response.sendRedirect("BookServlet");
				return;
			} else {// View login page
				request.getRequestDispatcher("jsp/login.jsp").forward(request,
						response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
