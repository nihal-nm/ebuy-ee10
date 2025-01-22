package com.ibm.ebuy.service;

import jakarta.ejb.Singleton;

/**
 * A Singleton bean which use No Interface View<br>
 * This EJB is use for record how many users access to eBuy
 * 
 * @author QiaoXi
 * 
 */
@Singleton
public class CounterBean {
	private int access = 0;

	/**
	 * Get amount of how many users has access to eBuy
	 * 
	 * @return
	 */
	public int getAccessAmount() {
		return access;
	}
	
 
	/**
	 * Add 1 user to amount
	 */
	public void increase() {
		access++;
	}	
}
