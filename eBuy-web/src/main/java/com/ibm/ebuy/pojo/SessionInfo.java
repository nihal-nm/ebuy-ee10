package com.ibm.ebuy.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.ibm.ebuy.entity.Order;

/**
 * Session data storage<br>
 * In session, there is only 'sessionInfo' attribute<br>
 * No other attribute
 * @author QiaoXi
 *
 */
public class SessionInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private List<Order> orderList;

	/**
	 * 
	 * @param username
	 *            Can be NULL if user not logon
	 */
	public SessionInfo(String username) {
		this.username = username;
		orderList = new CopyOnWriteArrayList<Order>();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	/**
	 * Check if an order with a certain isbn is exist
	 * 
	 * @param isbn
	 * @return Order if order exist<br>
	 *         NULL if order does not exist
	 */
	public Order getOrderByIsbn(int isbn) {
		if (this.orderList.isEmpty()) {
			return null;
		} else {
			for (Order o : this.orderList) {
				if (o.getIsbn() == isbn) {
					return o;
				}
			}
			return null;
		}
	}

	public void setOrder(Order order) {
		if (this.orderList.isEmpty()) {//No any order
			order.setTime(new Date());
			this.orderList.add(order);
		} else if (this.getOrderByIsbn(order.getIsbn()) == null) {
			//No order for this book, create
			this.orderList.add(order);
		} else {
			//order for this book exist, update
			for (Order o : this.orderList) {
				if (o.getIsbn() == order.getIsbn()) {
					order.setTime(new Date());
					o=order;
				}
			}
		}
	}
}
