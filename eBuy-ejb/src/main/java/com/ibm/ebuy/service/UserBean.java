package com.ibm.ebuy.service;

import jakarta.ejb.Local;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.ibm.ebuy.entity.User;
import com.ibm.ebuy.service.interfaces.UserBeanLocal;

/**
 * Session Bean implementation class UserBean
 * 
 * @author QiaoXi
 */
@Stateless
@Local(UserBeanLocal.class)
@LocalBean
public class UserBean implements UserBeanLocal {
	@PersistenceContext
	protected EntityManager em;

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	/**
	 * Default constructor.
	 */
	public UserBean() {
	}

	@Override
	public User checkUser(String username, String password) {
		Query query = em.createNamedQuery("checkUser");
		query.setParameter("username", username);
		query.setParameter("password", password);
		User user = null;
		try {
			user = (User) query.getSingleResult();
			return user;
		} catch (EntityNotFoundException e) {// No user found
			return null;
		} catch (Exception e) {// No user found
			return null;
		}
	}

	@Override
	public User getUserByName(String username) {
		Query query = em.createNamedQuery("getUserByName");
		query.setParameter("username", username);
		try {
			User user = (User) query.getSingleResult();
			return user;
		} catch (EntityNotFoundException e) {// No user found
			return null;
		} catch (NoResultException e) {// No user found
			return null;
		} catch (Exception e) {// No user found
			return null;
		}
	}

	@Override
	public int registerUser(String username, String password) {
		if (this.getUserByName(username) != null) {// User already exist
			return 1;
		} else {
			try {
				User user = new User();
				user.setUsername(username);
				user.setPassword(password);

				em.persist(user);
				em.flush();
				return 0;
			} catch (Exception e) {
				e.printStackTrace();
				return 9;
			}
		}
	}

	public int removeUser(String username) {
		try {
			User user = this.getUserByName(username);
			if (user != null) {
				em.remove(user);
				return 0;//success
			}
			return 1;//user not exist
		} catch (Exception e) {
			e.printStackTrace();
			return 9;//other error
		}
	}
}
