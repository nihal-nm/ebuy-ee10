<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>jsp/register.jsp</TITLE>
<LINK href="theme/Master.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY bgcolor="#ffffff" link="#000099">
	<%@ page session="false"%>
	<TABLE align=center width="500" id=T1>
		<tr>
			<td>
				<TABLE style="font-size: smaller" align=center id=T11>
					<TR>
						<TD bgcolor="#8080c0" align="left" width="400" height="10"
							colspan="5"><FONT color="#ffffff"><B>eBuy Login</B> </FONT>
						</TD>
						<TD align="center" bgcolor="#000000" width="100" height="10"><FONT
							color="#ffffff"><B>eBuy</B> </FONT></TD>
					</TR>
				</TABLE>

				<TABLE width="100%" id=T12>
					<TR>
						<TD width="4%" bgcolor="#e7e4e7" rowspan="10"></TD>
						<TD><B>Register with eBuy</B>
							<HR>
						</TD>
					</TR>
					<tr>
						<td><FORM action="RegisterServlet" method=post id=RegisterForm>
								<TABLE width="100%" id=T121>
									<TR>
										<TD align="right"><FONT size="-1">Username </FONT><INPUT
											size="20" type="text" name="username"></TD>
									</TR>
									<TR>
										<TD align="right"><FONT size="-1">Password </FONT><INPUT
											size="20" type="password" name="password1"></TD>
									</TR>
									<TR>
										<TD align="right"><FONT size="-1">Confirm </FONT><INPUT
											size="20" type="password" name="password2"></TD>
									</TR>
									<TR>
										<TD align="right"><input type="submit" name=register
											value="Register" />
										</TD>
									</TR>
								</TABLE>
							</FORM></td>
					</tr>
					<TR>
						<TD align="right"><FONT color="#ff0033"> <%=(request.getAttribute("message") == null) ? "" : request
					.getAttribute("message")%> </FONT></TD>
					</TR>
					<TR>
						<TD width="98%">
							<HR>
						</TD>
					</TR>

					<TR>
						<TD><FORM action="LoginServlet" id=LoginForm><FONT size="-1" color="#000000"><B>Already
									registered? &nbsp;</B> </FONT>					
								<INPUT type="submit" value="Login">
							 <FONT size="-1" color="#000000"><a href="/eBuy">Back
									to Home Page</a> </FONT></FORM>
						</TD>
					</TR>
				</TABLE>
				<TABLE height="54" style="font-size: smaller" id=T13>
					<TBODY>
						<TR>
							<TD colspan="2">
								<HR>
							</TD>
						</TR>
						<TR>
							<TD colspan="2"></TD>
						</TR>
						<TR>
							<TD bgcolor="#8080c0" align="left" width="500" height="10"><B><FONT
									color="#ffffff">eBuy Login</FONT> </B></TD>
							<TD align="center" bgcolor="#000000" width="100" height="10"><FONT
								color="#ffffff"><B>eBuy</B> </FONT></TD>
						</TR>
						<TR>
							<TD colspan="2" align="center">Created&nbsp;with&nbsp;IBM
								WebSphere Application Server and WebSphere Studio Application
								Developer<BR> Copyright 2000, IBM Corporation</TD>
						</TR>
					</TBODY>
				</TABLE></td>
		</tr>
	</TABLE>
</BODY>
</HTML>
