package com.ibm.ebuy.servlet;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.ibm.ebuy.entity.Book;
import com.ibm.ebuy.entity.Order;
import com.ibm.ebuy.pojo.BookPojo;
import com.ibm.ebuy.pojo.SessionInfo;
import com.ibm.ebuy.service.CounterBean;
import com.ibm.ebuy.service.UserCounterBean;
import com.ibm.ebuy.service.interfaces.BookBeanLocal;

/**
 * Servlet implementation class BookServlet
 * 
 * @author QiaoXi
 * 
 */
@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private BookBeanLocal bookBean;
	@EJB
	private CounterBean counterBean;

	@EJB
	private UserCounterBean userCounterBean;
	@EJB
	private UserCounterBean userCounterBean2;

	public BookServlet() {
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
		if (request.getParameter("addBookToCart") != null) {// Add a book
			this.addBookToCart(request);
			this.returnToJSP(request, response);
			return;
		} else {// Will list all books by default
			counterBean.increase();// counter +1 when access this page
			userCounterBean.increase();
			this.returnToJSP(request, response);
			return;
		}
	}

	/**
	 * 
	 * @param request
	 */
	private void addBookToCart(HttpServletRequest request) {
		try {
			int isbn = Integer.valueOf(request.getParameter("isbn"));

			// Get user session, if no session exist, create one
			// The "username" attribute can be NULL if user not logon
			HttpSession session = request.getSession(true);
			SessionInfo sessionInfo;

			if (session.getAttribute("sessionInfo") == null) {
				// User not logon, create new session info
				sessionInfo = new SessionInfo(null);
				session.setAttribute("sessionInfo", sessionInfo);
			} else {
				sessionInfo = (SessionInfo) session.getAttribute("sessionInfo");
			}
			// Will be NULL if user not logon
			String username = sessionInfo.getUsername();

			Order order = sessionInfo.getOrderByIsbn(isbn);
			if (order != null) {// Order with isbn already exist
				order.setQuantity(order.getQuantity() + 1);
			} else {// Order with isbn NOT exist
				order = new Order();
				order.setUsername(username);
				order.setIsbn(isbn);
				order.setQuantity(1);
			}
			// System.out.println(sessionInfo.getOrderList().size());
			sessionInfo.setOrder(order);
			session.setAttribute("sessionInfo", sessionInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Return to list.jsp
	 * 
	 * @param request
	 * @param response
	 */
	private void returnToJSP(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.setAttribute("amount", counterBean.getAccessAmount());
			request.setAttribute("usercount", userCounterBean.getCounter());
			request.setAttribute("bookList", getBookList((SessionInfo) request
					.getSession().getAttribute("sessionInfo")));
			request.getRequestDispatcher("/jsp/list.jsp").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get all book info for JSP
	 * 
	 * @param sessionInfo
	 * @return
	 */
	private List<BookPojo> getBookList(SessionInfo sessionInfo) {
		List<BookPojo> bookList = new CopyOnWriteArrayList<BookPojo>();
		int quantity = 0;

		if (sessionInfo == null || sessionInfo.getOrderList() == null
				|| sessionInfo.getOrderList().isEmpty()) {
			for (Book book : bookBean.getAllBooks()) {
				BookPojo b = new BookPojo();
				b.setIsbn(book.getIsbn());
				b.setTitle(book.getTitle());
				b.setPrice(book.getPrice());
				b.setQuantity(quantity);
				bookList.add(b);
			}
		} else {
			List<Order> orderList = sessionInfo.getOrderList();
			for (Book book : bookBean.getAllBooks()) {
				for (Order o : orderList) {
					if (o.getIsbn() == book.getIsbn()) {
						quantity = o.getQuantity();
					}
				}
				BookPojo b = new BookPojo();
				b.setIsbn(book.getIsbn());
				b.setTitle(book.getTitle());
				b.setPrice(book.getPrice());
				b.setQuantity(quantity);
				bookList.add(b);
				quantity = 0;
			}
		}
		return bookList;
	}
}
