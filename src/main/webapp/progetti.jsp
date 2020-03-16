<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*" import="it.contrader.dto.ProgettiDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Progetti Management">
<meta name="author" content="Vittorio Valent">
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>Progetti Manager</title>

</head>
<body>
	<%@ include file="./css/header.jsp"%>

	<div class="navbar">
		<a href="/homeadmin.jsp">Home</a>
		<a href=/user/getall>Users</a>
		<a class="active" href=/progetti/getall>Progetti</a>
		<a href=/support/getall>Support</a>
		<a href=/codes/getall>Codes</a>
		<a href=/test/getall>Test</a>
		<a href="/user/logout" id="logout">Logout</a>
	</div>

	<div class="main">
		<%
			List<ProgettiDTO> list = (List<ProgettiDTO>) request.getSession().getAttribute("list");
		%>

		<br>

		<table>
			<tr>
				<th>Nome</th>
				<th>Data inserimento</th>
				<th>Data modifica</th>
				<th>Proprietario</th>
				<th></th>
				<th></th>
			</tr>
			<%
				for (ProgettiDTO u : list) {
			%>
			<tr>
				<td><a href="/progetti/read?id=<%=u.getId()%>"> <%=u.getNome()%>
				</a></td>
				<td><%=u.getData_i()%></td>
				<td><%=u.getData_m()%></td>
				<td><%=u.getUser() %></td>
				<td><a href="/progetti/preupdate?id=<%=u.getId()%>">Edit</a></td>


				<td><a href="/progetti/delete?id=<%=u.getId()%>">Delete</a></td>

			</tr>
			<%
				}
			%>
		</table>



		<form id="floatright" action="/progetti/insert" method="post">
			<div class="row">
				<div class="col-25">
					<label for="progetti">Nome progetto</label>
				</div>
				<div class="col-75">
					<input type="text" id="progetto" name="nome"
						placeholder="inserisci nome">
				</div>
			</div>
			
			<button type="submit">Insert</button>
		</form>

	</div>
	<br>
	<%@ include file="./css/footer.jsp"%>
</body>
</html>