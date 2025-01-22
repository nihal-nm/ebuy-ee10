package com.ibm.ebuy.service.interfaces;

import com.ibm.ebuy.entity.User;

public interface UserBeanLocal {
	/**
	 * Return a boolean value to check a username with password is exist
	 * 
	 * @param username
	 * @param password
	 * @return NULL value will be returned if user does not exist
	 */
	public User checkUser(String username, String password);

	/**
	 * Register a new user and save to DB
	 * 
	 * @param username
	 * @param password
	 * @return 0 - Register succeed<br>
	 *         1 - Username already exist <br>
	 *         9 - Unknown error occur<br>
	 */
	public int registerUser(String username, String password);

	/**
	 * Return a User instance with a certain username
	 * 
	 * @param username
	 * @return User - If username exist<br>
	 *         NULL - If username does not exist
	 */
	public User getUserByName(String username);

	/**
	 * 
	 * @param username
	 * @return 0 - Remove succeed <br>
	 *         1 - Username not exist <br>
	 *         9 - Unknown error occur
	 */
	public int removeUser(String username);
}
