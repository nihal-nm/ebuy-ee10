package com.ibm.ebuy.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import jakarta.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.ibm.ebuy.entity.Order;
import com.ibm.ebuy.pojo.SessionInfo;
import com.ibm.ebuy.service.interfaces.BookBeanLocal;
import com.ibm.ebuy.service.interfaces.OrderBeanLocal;

/**
 * Servlet implementation class PayServlet
 * 
 * @author QiaoXi
 */
@WebServlet("/PayServlet")
public class PayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StatLogger statLogger = new StatLogger(1000);
	
	 @EJB
	BookBeanLocal bookBean;
	 @EJB
	OrderBeanLocal orderBean;

	/**
	 * Use JNDI method to get EJB instance
	 */
	private void jndiLookup() {
		Context initialContext;
		try {
			System.out.println("init");
			initialContext = new InitialContext();
			bookBean = (BookBeanLocal) initialContext
			//.lookup("java:comp/env/ejb/BookBean");
					 .lookup("ejb/BookBean");
			orderBean = (OrderBeanLocal) initialContext
					.lookup("ejb/OrderBean");
			System.out.println(bookBean);
			System.out.println(orderBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PayServlet() {
		super();
	//	jndiLookup();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		requestHandler(request, response);
		jndiLookup();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		requestHandler(request, response);
	}

	private void requestHandler(HttpServletRequest request,
			HttpServletResponse response) {
		// Request is filtered by LoginFilter, so the session info must be exist
		SessionInfo sessionInfo = (SessionInfo) request.getSession()
				.getAttribute("sessionInfo");
		String username = sessionInfo.getUsername();
		List<Order> orderList = sessionInfo.getOrderList();
		String message = "";
		if (request.getParameter("confirm") != null) {// Pay
			for (Order o : orderList) {// Save order to DB
				o.setUsername(username);
				o.setTime(new Date());
				o.setIspaid(1);// Order is paid
				orderBean.saveOrderToDB(o);
			}
			orderList.clear();// clean orderList
			try {
				message = "Pay successful, you can continue shopping";
				request.setAttribute("message", message);
				request.getRequestDispatcher("/jsp/pay.jsp").forward(request,
						response);
				this.statLogger.incrementBuyCounter();

				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {// View total price
			BigDecimal total = new BigDecimal("0");
			for (Order o : orderList) {
				double price = bookBean.getBookByISBN(o.getIsbn()).getPrice();
				BigDecimal p = new BigDecimal(Double.toString(price));
				BigDecimal q = new BigDecimal(Integer.toString(o.getQuantity()));
				total = total.add(p.multiply(q));
			}
			try {
				message = "You need to pay:" + total;
				request.setAttribute("message", message);
				request.getRequestDispatcher("/jsp/pay.jsp").forward(request,
						response);
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
