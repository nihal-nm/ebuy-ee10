package com.ibm.ebuy.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

/**
 * JPA entity bean for ORDER table
 * @author QiaoXi
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "getAllOrders", query = "SELECT o FROM Order o ORDER BY o.id"),
		@NamedQuery(name = "getAllOrdersByUser", query = "SELECT o FROM Order o WHERE o.username = :username"),
		@NamedQuery(name = "getUnpaidOrderByUser", query = "SELECT o FROM Order o WHERE o.username = :username AND o.ispaid=0") })
public class Order implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "ISBN")
	private int isbn;
	@Column(name = "QUANTITY")
	private int quantity;
	@Column(name = "USERNAME")
	private String username;
	@Column(name = "TIME")
	private Date time;
	@Column(name = "ISPAID")
	private int ispaid;

	private static final long serialVersionUID = 1L;

	public Order() {
		super();
		quantity = 0;
		time = new Date();
		ispaid = 0;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getIspaid() {
		return ispaid;
	}

	public void setIspaid(int ispaid) {
		this.ispaid = ispaid;
	}
}
