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

import com.ibm.ebuy.entity.Order;
import com.ibm.ebuy.pojo.BookPojo;
import com.ibm.ebuy.pojo.SessionInfo;
import com.ibm.ebuy.service.Calculator;
import com.ibm.ebuy.service.interfaces.BookBeanLocal;

/**
 * Handle shopping cart request
 * 
 * @author QiaoXi
 * 
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private BookBeanLocal bookBean;
	@EJB
	// @Inject
	private Calculator calculator;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartServlet() {
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
			if (request.getParameter("add") != null) {// Quantity+1
				modifyOrder(request, true);
				returnToJSP(request, response);
				return;
			} else if (request.getParameter("remove") != null) {// Quantity-1
				modifyOrder(request, false);
				returnToJSP(request, response);
				return;
			} else if (request.getParameter("back") != null) {// To list.jsp
				response.sendRedirect("BookServlet");
				return;
			} else {// View shopping cart
				returnToJSP(request, response);
				return;
			}
		} catch (NullPointerException e) {// No session or no order
			try {
				request.getRequestDispatcher("BookServlet").forward(request,
						response);
				return;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				request.getRequestDispatcher("BookServlet").forward(request,
						response);
				return;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void returnToJSP(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute("bookList", getBookList(request));
		request.getRequestDispatcher("/jsp/cart.jsp").forward(request, response);
	}

	/**
	 * Add or remove 1 book from order<br>
	 * If book quantity=0, the book will be removed from the order
	 * 
	 * @param request
	 * @param response
	 * @param add
	 *            : True - add 1 book; False - remove 1 book
	 * @throws Exception
	 */
	private void modifyOrder(HttpServletRequest request, boolean add)
			throws Exception {
		String isbn = request.getParameter("isbn");
		HttpSession session = request.getSession();
		SessionInfo sessionInfo = (SessionInfo) session
				.getAttribute("sessionInfo");
		List<Order> orderList = sessionInfo.getOrderList();
		for (Order o : orderList) {
			if (String.valueOf(o.getIsbn()).equals(isbn)) {
				if (add) {
					o.setQuantity(o.getQuantity() + 1);
				} else {
					o.setQuantity(o.getQuantity() - 1);
					if (o.getQuantity() < 1) {// Book order=0
						orderList.remove(o);
					}
				}				
			}
		}
		session.setAttribute("sessionInfo", sessionInfo);
	}

	/**
	 * Get all book info for JSP
	 * 
	 * @param sessionInfo
	 * @return
	 */
	private List<BookPojo> getBookList(HttpServletRequest request) {

		List<BookPojo> bookList = new CopyOnWriteArrayList<BookPojo>();
		HttpSession session = request.getSession();
		SessionInfo sessionInfo = (SessionInfo) session
				.getAttribute("sessionInfo");
		if (sessionInfo == null || sessionInfo.getOrderList() == null
				|| sessionInfo.getOrderList().isEmpty()) {
			// Do nothing
		} else {// Save order to BookPojo
			List<Order> orderList = sessionInfo.getOrderList();
			// Use BigDecimal for double number calculate
			String total = "0";
			for (Order o : orderList) {// Save order info to BookPojo
				BookPojo b = new BookPojo();
				b.setIsbn(o.getIsbn());
				b.setTitle(bookBean.getBookByISBN(o.getIsbn()).getTitle());
				b.setQuantity(o.getQuantity());
				b.setPrice(bookBean.getBookByISBN(o.getIsbn()).getPrice());
				bookList.add(b);
				if (calculator == null) {
					System.out.println("@Inject fail to inject EJB");
					request.setAttribute("total", total);
					return bookList;
				}
				total = calculator.add(total, calculator.multiply(
						String.valueOf((b.getPrice())),
						String.valueOf(b.getQuantity())));
				request.setAttribute("total", total);
			}
		}
		return bookList;
	}
}
