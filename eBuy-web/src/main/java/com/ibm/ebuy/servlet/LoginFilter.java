package com.ibm.ebuy.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.ibm.ebuy.entity.Order;
import com.ibm.ebuy.pojo.SessionInfo;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(urlPatterns = { "/jsp/pay.jsp"}, servletNames = { "com.ibm.ebuy.servlet.PayServlet" })
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse rsp, FilterChain chain) throws IOException, ServletException {
		SessionInfo sessionInfo = null;
		String username = null;
		List<Order> orderList = null;
		HttpServletRequest	request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)rsp;
		try {// Try to get session, if no session exist, an exception throws out
			sessionInfo = (SessionInfo) request.getSession().getAttribute(
					"sessionInfo");
			username = sessionInfo.getUsername();
			orderList = sessionInfo.getOrderList();
		} catch (NullPointerException e) {// No session
			try {//Redirect to book list
				response.sendRedirect("BookServlet");
				return;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {// Other error occur, return to book list
			try {
				response.sendRedirect("BookServlet");
				return;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (username == null) {// User not login, return to login page
			try {
				response.sendRedirect("LoginServlet");
				return;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (orderList == null || orderList.isEmpty()) {// No order
			try {
				request.getRequestDispatcher("BookServlet").forward(request,
						response);
				return;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
