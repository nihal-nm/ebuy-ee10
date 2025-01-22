<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN final">
<HTML>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*, jakarta.servlet.http.*,com.ibm.ebuy.pojo.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="theme/Master.css" rel="stylesheet" type="text/css">
<TITLE>jsp/index.jsp</TITLE>
</HEAD>
<BODY>
	<CENTER>
		<h1>eBuy BookStore</h1>
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
						<tr><td>Total access: ${amount}</td></tr>
						<tr><td>You access: ${usercount}</td></tr>
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
							<td align=center width=20%>Add</td>
							<td align=center width=20%>Order</td>
						</tr>
						<c:forEach items="${bookList}" var="book">
							<tr>
								<td align="center"><img src="images/${book.isbn}.jpg"
									border="0" alt="${book.title}" width="100" height="120">
								</td>
								<td>
									<h2>${book.title}</h2> ISBN: ${book.isbn}<br /> Price:
									${book.price}</td>
								<td align=center><FORM action="BookServlet" method="post" id="${book.isbn}">
										<input type=hidden name=isbn value="${book.isbn}" /> <INPUT
											type="submit" name="addBookToCart" value="Add To Cart" />
									</FORM>
								</td>
								<td align=center>${book.quantity}</td>
							</tr>
						</c:forEach>
					</table></td>
			</tr>
		</table>
		<a href="/eBuy">Back to Home Page</a>
	</CENTER>
</BODY>
</HTML>