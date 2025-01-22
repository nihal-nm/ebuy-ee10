package com.ibm.ebuy.service.interfaces;

import java.util.List;

import com.ibm.ebuy.entity.Order;

public interface OrderBeanLocal {
	public void saveOrderToDB(Order order);

	public List<Order> getAllOrdersByUser(String username);
	
	public void payOrder(String username);
}
