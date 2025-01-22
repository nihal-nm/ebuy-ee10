<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN final">
<HTML>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*, jakarta.servlet.http.*,com.ibm.ebuy.pojo.*,java.math.BigDecimal"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="theme/Master.css" rel="stylesheet" type="text/css">
<TITLE>jsp/cart.jsp</TITLE>
</HEAD>
<BODY>
	<CENTER>
		<h1>eBuy Shopping Cart</h1>
		<table border=0 id="T1">
			<tr>
				<td width=20% valign="top"><table id="T11">

						<tr>
							<td align="right">
								<%
									SessionInfo sessionInfo = (SessionInfo) session
											.getAttribute("sessionInfo");
									if (sessionInfo != null && sessionInfo.getUsername() != null) {
								%>Welcome: <%=sessionInfo.getUsername()%><br>
								<FORM action="LoginServlet" id="LogoutForm">
									<INPUT type="submit" value="Logout" name=logout />
								</FORM> <%
 	} else {
 %><FORM action="LoginServlet" id="LoginForm">
									<INPUT type="submit" value="Login" />
								</FORM> <%
 	}
 %>
							</td>
						</tr>
						<tr>
							<td><hr></td>
						</tr>
						<tr>
							<td align="right"><FORM action="RegisterServlet" id="RegisterForm">
									<INPUT type="submit" value="Register" />
								</FORM>
							</TD>
						</tr>
						<tr>
							<td align="right"><FORM action="CartServlet" id="CartForm">
									<INPUT type="submit" value="View Cart">
								</FORM>
							</td>
						</tr>
					</table>
				</td>
				<td><table border="1" bgcolor="#99AACC" cellpadding="0"
						cellspacing="0" width="600" id="T12">
						<%
							request.setAttribute("bookList", request.getAttribute("bookList"));
						%>
						<tr>
							<td colspan="10" background="images/ws_background.jpg">&nbsp;</td>
						</tr>
						<tr>
							<td align=center width=20%>Cover</td>
							<td align=center width=40%>Book Info</td>
							<td align=center width=20%>Order</td>
						</tr>
						<c:forEach items="${bookList}" var="book">
							<tr>
								<td align="center"><img src="images/${book.isbn}.jpg"
									border="0" alt="${book.title}" width="50" height="60">
								</td>
								<td>${book.title}<br> ISBN: ${book.isbn}<br /> Price:
									${book.price}</td>

								<td align=center>${book.quantity}
									<FORM action="CartServlet" method="post" id="${book.isbn}">
										<input type=hidden name=isbn value="${book.isbn}" /> <INPUT
											type="submit" name="add" value="+" /><INPUT type="submit"
											name="remove" value="-" />
									</FORM></td>
							</tr>
						</c:forEach>
						<tr>
							<td align=right colspan=5><B>Total: ${total }</B>
							</td>
						</tr>
					</table>
					<table border=0 align=right id="T13">
						<tr>
							<td><FORM action="BookServlet" method=post id="BookForm">
									<INPUT type="submit" value="Continue Buy">
								</FORM>
							</td>
							<td><FORM action="PayServlet" method=post id="PayForm">
									<h2>
										<b> <INPUT type="submit" name=pay value="Pay"> </b>
									</h2>
								</FORM></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<a href="/eBuy">Back to Home Page</a>
	</CENTER>
</BODY>
</HTML>