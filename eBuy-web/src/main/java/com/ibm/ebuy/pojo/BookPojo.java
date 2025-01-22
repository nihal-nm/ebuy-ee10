package com.ibm.ebuy.pojo;

/**
 * Use for JSP data store
 * 
 * @author QiaoXi
 * 
 */
public class BookPojo {
	private String title;
	private int isbn;
	private double price;
	private int quantity;
	
	public  String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}	
}
