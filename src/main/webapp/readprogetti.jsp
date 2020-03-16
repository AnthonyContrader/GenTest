<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="it.contrader.dto.ProgettiDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Progetti read">
<meta name="author" content="Vittorio Valent">
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>Read Progetti</title>
</head>
<body>
	<%@ include file="./css/header.jsp"%>
	<div class="navbar">
		<a  href="/homeadmin.jsp">Home</a>
		<a href=/user/getall>Users</a>
		<a class="active" href=/progetti/getall>Progetti</a>
		<a href=/support/getall>Support</a>
		<a href=/codes/getall>Codes</a>
		<a href=/test/getall>Test</a>
		<a href="/user/logout" id="logout">Logout</a>
	</div>
	<br>
	
	<div class = "main">
		<%
		ProgettiDTO u = (ProgettiDTO) request.getSession().getAttribute("dto");
		
		%>
	
	<table>
			<tr>
				<th> Id </th>
				<th>Nome</th>
				<th>Data inserimento</th>
				<th>Data modifica</th>
				<th>Proprietario</th>
			</tr>
			<tr>
				<td><%=u.getId() %></td>
				<td><%=u.getNome() %></td>
				<td><%=u.getData_m() %></td>
				<td><%=u.getData_i() %></td>
				<td><%=u.getUser() %></td>
			</tr>
	</table>
	<br> <br> <br> <br> <br> <br> <br>
		<br> <br> <br> <br> <br> <br> <br>
	</div>
	<%@ include file="./css/footer.jsp"%>
</body>
</html>