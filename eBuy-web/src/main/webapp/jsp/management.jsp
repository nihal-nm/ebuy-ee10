<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN final">
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="theme/Master.css" rel="stylesheet" type="text/css">
<TITLE>management.jsp</TITLE>
</HEAD>
<BODY>
	<CENTER>
		<H1>
			<FONT color="blue">eBuy Management</FONT>
		</H1>
		<img border="0" src="images/bookstore.jpg" width="647" height="100"
			align="middle" alt="BOOKSTORE">
	</CENTER>

	<TABLE border=1 align="center" id=T1>
		<TR>
			<TD align=center bgcolor="#99AACC" width=180>Manage Users for
				Stress (user1 ~ user1000)<br>
				<FORM action="StressUserServlet" id=StressUserForm>
					<INPUT type=hidden name=add value=true> <INPUT
						type="submit" value="Add Users">
				</FORM>
				<FORM action="StressUserServlet" id=StressUserForm2>
					<INPUT type=hidden name=remove value=true> <INPUT
						type="submit" value="Remove Users">
				</FORM>
			</TD>
			<TD align=center bgcolor=orange width=180><FONT size=3> <b>eBuy
						management </b>
			</FONT> <img border=0 src="images/manage.PNG" width="125" height="125" />
				<FORM action="/eBuy-ext/index.jsp" id=ExtForm>
					<INPUT type="submit" value="">
				</FORM></TD>

			<TD align=center bgcolor="#99AA99" width=180><FONT size="3"><B>JavaMail</B>
			</FONT>
				<FORM action="javamail/config.jsp" id=OtherForm>

					<INPUT type="submit" value="Enter JavaMail" disabled="disabled">

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
			<TD><IMG border="0" src="images/was_footer.jpg" width="647"
				height="58"></TD>
		</TR>

	</TABLE>
	<center>
		<a href="/eBuy">Back to Home Page</a>
	</center>
</BODY>
</HTML>
