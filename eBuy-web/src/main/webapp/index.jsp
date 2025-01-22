<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN final">
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="theme/Master.css" rel="stylesheet" type="text/css">
<TITLE>index.jsp</TITLE>
</HEAD>
<BODY>
	<center>
		<h1>
			<FONT color="blue">eBuy</FONT>
		</h1>
		<img border="0" src="images/bookstore.jpg" width="647" height="100"
			align="middle" alt="BOOKSTORE">

		<P align="center">
			<IMG border="0" src="images/server.jpg" width="185" height="61"
				align="middle" alt="">
		</P>
	</center>

	<TABLE border=1 align="center" id=T1>
		<TR>
			<TD align=center bgcolor="#99AACC" width=180><font size=3>
					<b>Bookstore<br> 
				</b>
			</font> <img border=0 src="images/shopping_cart.gif" width="125"
				height="125" />
				<FORM action="BookServlet" id=BookForm>
					<INPUT type="submit" value="Enter eBuy">
				</FORM></TD>
			<TD align=center bgcolor="#CC7711" width=180><font size=3> <b>eBuy
						management </b>
			</font> <img border=0 src="images/manage.PNG" width="125" height="125" />
				<FORM action="StressUserServlet" id=ManagementForm>
					<INPUT type="submit" value="Enter management">
				</FORM></TD>
<TD align=center bgcolor="#88CC77" width=180><font size=3> <b>eBuy
						WebSocket </b>
			</font> <img border=0 src="images/ext.PNG" width="125" height="125" />
				<FORM action="/eBuy-ext/index.jsp" id=ExtForm>
					<INPUT type="submit" value="WebSocket">
				</FORM></TD>
			 
		</tr>
	</TABLE>

	<TABLE align=center border=0 id=T2>
		<TR>
			<TD align=center>Power by <FONT color="purple"><B>
						IBM WebSphere 8.5 </B> </font></TD>
		</TR>
		<TR>
		</TR>
		<TR>
			<TD><img border="0" src="images/was_footer.jpg" width="647"
				height="58"></TD>
		</TR>

	</TABLE>
</BODY>
</HTML>
