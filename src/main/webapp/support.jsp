<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*" import="it.contrader.dto.SupportDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Support Management">
<meta name="author" content="Vittorio Valent">
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>Support Manager</title>
</head>
<body>
	<%@ include file="./css/header.jsp" %>

	<div class="navbar">
		<a href="/homeadmin.jsp">Home</a>
		<a href=/user/getall>Users</a>
		<a href=/progetti/getall>Progetti</a>
		<a class="active" href=/support/getall>Support</a>
		<a href=/codes/getall>Codes</a>
		<a href=/test/getall>Test</a>
		<a href="/user/logout" id="logout">Logout</a>
	</div>
	<div class="main">
		<%
		List<SupportDTO> list = (List<SupportDTO>) request.getSession().getAttribute("list");
		%>
		
		<br>
		
		<table>
			<tr>
				<th>Domanda</th>
				<th>Risposta</th>
				<th></th>
				<th></th>
			</tr>
			<%
				for(SupportDTO u:list){
			%>
			<tr>
			<td><a href="/support/read?id=<%=u.getId()%>"> <%=u.getDomanda()%> </a>
			</td>
			<td><%=u.getRisposta() %></td>
			<td> <a href="/support/preupdate?id=<%=u.getId() %>">Edit</a></td>
			<td> <a href="/support/delete?id=<%=u.getId() %>">Delete</a></td>
			</tr>
			<%
				}
			%>
		</table>
	
		<form id="floatright" action="/support/insert" method="post">
			<div class="row">
				<div class="col-25">
					<label for="progetti">Domanda</label>
				</div>
				<div class="col-75">
					<input type="text" id="domanda" name="domanda"
						placeholder="inserisci domanda">
				</div>
			</div>
			<div class="row">
				<div class="col-25">
					<label for="support">Risposta</label>
				</div>
				<div class="col-75">
					<input type="text" id="domanda" name="risposta"
						placeholder="inserisci risposta">
				</div>
			</div>
			<button type="submit">Insert</button>
		</form>
	</div>
	<br>
	<%@ include file="./css/footer.jsp" %>
</body>
</html>