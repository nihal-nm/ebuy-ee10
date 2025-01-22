package com.ibm.ebuy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import javax.sql.DataSource;

import com.ibm.ebuy.entity.Book;
import com.ibm.ebuy.service.interfaces.BookBeanLocal;

/**
 * Session Bean implementation class BookBean
 * 
 * @author QiaoXi
 */

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class BookBean implements BookBeanLocal {
	// @Resource(mappedName = "jdbc/ebuy")
	// @Resource(name="java:global/env/jdbc/ebuy")//Work on Liberty
	@Resource(name = "jdbc/ebuy")
	// Work on Liberty
	// @Resource(lookup = "ebuyDS")
	private DataSource ds;	
	private EntityManager em;

	@PersistenceContext(unitName = "ebuy")
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	/**
	 * Default constructor.
	 */
	public BookBean() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getAllBooks() {
		List<Book> bookList = new CopyOnWriteArrayList<Book>();
		Query query = em.createNamedQuery("getAllBooks");
		bookList = query.getResultList();
		return bookList;
	}

	@Override
	public Book getBookByISBN(int isbn) {
		// Query query = em.createNamedQuery("getBookByISBN");
		// query.setParameter("isbn", isbn);
		// return (Book) query.getSingleResult();

		Book b = new Book();
		try {
			Connection conn = ds.getConnection();

			// ebuy.Book must be defined
			String sql = "SELECT * FROM ebuy.Book b WHERE b.isbn=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, isbn);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				b.setIsbn(rs.getInt(1));// Column id start from 1
				b.setTitle(rs.getString(2));
				b.setPrice(rs.getDouble(3));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
}
