package com.ibm.ebuy.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import jakarta.ejb.Local;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.ibm.ebuy.entity.Order;
import com.ibm.ebuy.service.interfaces.OrderBeanLocal;

/**
 * Session Bean implementation class OrderBean
 * @author QiaoXi
 */
@Stateless
@Local(OrderBeanLocal.class)
@LocalBean
public class OrderBean implements  OrderBeanLocal {
	 
	private EntityManager em;

	@PersistenceContext(unitName = "ebuy")
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	/**
	 * Default constructor.
	 */
	public OrderBean() {
	}

	@Override
	public void saveOrderToDB(Order order) {
		em.persist(order);
		em.flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getAllOrdersByUser(String username) {
		List<Order> orderList = new CopyOnWriteArrayList<Order>();
		Query query = em.createNamedQuery("getAllOrdersByUser");
		query.setParameter("username", username);
		orderList = query.getResultList();
		return orderList;
	}

	@Override
	public void payOrder(String username) {

	}

}
