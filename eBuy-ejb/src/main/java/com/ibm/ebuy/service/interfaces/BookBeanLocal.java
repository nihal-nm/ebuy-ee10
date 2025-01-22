package com.ibm.ebuy.service.interfaces;

import java.util.List;

import jakarta.ejb.Local;

import com.ibm.ebuy.entity.Book;

@Local
public interface BookBeanLocal {
	/**
	 * Return all books as a List
	 * @return List<Book>
	 */
	public List<Book> getAllBooks();
	
	/**
	 * Get a book with a certain ISBN
	 * @param isbn
	 * @return Book
	 */
	public Book getBookByISBN(int isbn);
}
