package com.ibm.ebuy.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

/**
 * JPA entity bean for USER table
 * @author QiaoXi
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "getAllUsers", query = "SELECT u FROM User u ORDER BY u.username"),
		@NamedQuery(name = "getUserByName", query = "SELECT u FROM User u WHERE u.username = :username"),
		@NamedQuery(name = "checkUser", query = "SELECT u FROM User u WHERE u.username = :username AND u.password=:password") })
public class User implements Serializable {
	@Id
	private String username;
	@Column(name="PASSWORD")
	private String password;

	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
