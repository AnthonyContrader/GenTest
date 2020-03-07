<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "it.contrader.dto.SupportDTO"
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Read Support</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<div class="navbar">
	<a href="homeadmin.jsp">Home</a>
	  <a class="active"  href="UserServlet?mode=userlist">Users</a>
	  <a href="ProgettiServlet?mode=progettilist">Projects</a>
	<a class="active" href="SupportServlet?mode=supportlist">Support</a>
	<a href="LogoutServlet" id ="logout">Logout</a>
</div>
<br>
<div class= "main">
<%
	SupportDTO u = (SupportDTO) request.getAttribute("dto");
%>
<table>
		<tr>
			<th>Q:</th>
			<th>A:</th>
		</tr>
		<tr>
			<td> <%=u.getDomanda() %> </td>
			<td> <%=u.getRisposta() %> </td>
		</tr>
	</table>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
</div>
<%@ include file="../css/footer.jsp" %>
</body>
</html>