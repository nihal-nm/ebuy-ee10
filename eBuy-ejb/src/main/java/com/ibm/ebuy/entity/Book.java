package com.ibm.ebuy.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

/**
 * JPA entity bean for BOOK table<br>
 * Only offer get method, not set allowed
 * @author QiaoXi
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "getAllBooks", query = "SELECT b FROM Book b ORDER BY b.isbn"),
		@NamedQuery(name = "getBookByISBN", query = "SELECT b FROM Book b WHERE b.isbn = :isbn"),
		@NamedQuery(name = "getBookByTitle", query = "SELECT b FROM Book b WHERE b.title = :title"),
		@NamedQuery(name = "getTotal", query = "SELECT count(b) FROM Book b") })
public class Book implements Serializable {
	@Id
	@Column(name = "isbn")
	private int isbn;
	@Column(name = "TITLE")
	private String title;
	@Column(name = "PRICE")
	private double price;

	private static final long serialVersionUID = 1L;

	public Book() {
		super();
	}

	public int getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	public double getPrice() {
		return price;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
