package com.ibm.ebuy.service;

import java.math.BigDecimal;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

/**
 * Session Bean implementation class Calculator
 */
@Stateless
@LocalBean
public class Calculator {
	public Calculator() {
	}

	/**
	 * 
	 * @param a
	 *            String can be convert to a number
	 * @param b
	 *            String can be convert to a number
	 * @return String of the add result
	 */
	public String add(String a, String b) {
		BigDecimal p = new BigDecimal(a);
		BigDecimal q = new BigDecimal(b);
		return p.add(q).toString();
	}

	/**
	 * 
	 * @param a
	 *            String can be convert to a number
	 * @param b
	 *            String can be convert to a number
	 * @return String of the multiple result
	 */
	public String multiply(String a, String b) {
		BigDecimal p = new BigDecimal(a);
		BigDecimal q = new BigDecimal(b);
		return p.multiply(q).toString();
	}
}
