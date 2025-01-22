package com.ibm.ebuy.service;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;

/**
 * UserCounterBean is use for record how many pages that user total accessed<br>
 * 
 * @author QiaoXi
 * 
 */
//@Stateful
 @Stateless
@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class UserCounterBean {
	private int counter = 0;
	private int counter1 = 0;
//	private UserTransaction umt;

	/**
	 * Default constructor.
	 */
	public UserCounterBean() {

//		Context ctx;
//		try {
//			ctx = new InitialContext();
//			umt = (UserTransaction) ctx
//					.lookup("jakarta.transaction.UserTransaction");
//			umt.begin();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

//	@SuppressWarnings("null")
//	public void increase() {
//		counter1++;
//		if (counter % 2 == 0) {
//			// A NullPointerException will be occurred, test UserTraction
//			try {
//				umt.begin();
//				counter += 2;
//				System.out.println("begin"+counter);
//				String test = null;
//				test.toString();//NPE occur
//				umt.commit();
//			} catch (NullPointerException e1) {
//				try {
//					umt.rollback();
//					System.out.println("rollback"+counter);
//					counter++;
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		} else {
//			try {
//				umt.begin();
//				counter++;
//				umt.commit();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}

	public void increase() {
		counter++;
		counter1++;
	}
	/**
	 * 
	 * @return If error occurred, -1 will return
	 */
	public int getCounter() {
		if (counter != counter1) {
			// error occurred, counter does not correct
			return -1;
		} else {
			return counter;
		}
	}
}
