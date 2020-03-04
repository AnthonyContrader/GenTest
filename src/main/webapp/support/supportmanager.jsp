<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="it.contrader.dto.SupportDTO"
    import="java.util.List"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Support Manager</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>

<div class="navbar">
	<a href="homeadmin.jsp">Home</a>
	<a href="UserServlet?mode=userlist">Users</a>
	<a href="ProgettiServlet?mode=progettilist"> Projects </a>
	<a class="active" href="SupportServlet?mode=supportlist">Support</a>
	<a href="logoutServlet" id ="logout">Logout</a>
</div>
<div class="main">

	<%
	List<SupportDTO> list= (List<SupportDTO>) request.getAttribute("list");
	%>
<br>

	<table>
		<tr>
			<th>Q:</th>
			<th>A:</th>
			<th></th>
			<th></th>
		</tr>
	
		<%
		
		 	for(SupportDTO u : list) {
		 		
		%>
		<tr>
			<td><a href=SupportServlet?mode=read&id=<%=u.getId() %>>
					<%=u.getDomanda() %>
			 </a></td>
			<td><%=u.getRisposta() %></td>
		
			<td><a href=SupportServlet?mode=read&update=true&id=<%=u.getId()%>>Edit</a>
			</td>
			<td><a href=SupportServlet?mode=delete&id=<%=u.getId()%>>Delete</a>
			</td>

		</tr>
		<%
			}
		%>
	
	</table>

<form id="floatright" action="SupportServlet?mode=insert" method ="post">
<div class="row">
	<div class="col-25">
	<label for="domanda">Q:</label>
	</div>
	<div class="col-75">
		<input type="text" id="domanda" name="domanda" placeholder="inserisci domanda">
	</div>
</div>
<div class="row">
	<div class="col-25">
	<label for="risposta">A:</label>
	</div>
	<div class ="col-75">
	<input type="text" id="risposta" name="risposta" placeholder="inserisci risposta">
	</div>
	</div>
		<button type="submit">Insert</button>
</form>
</div>
<br>
<%@ include file="../css/footer.jsp" %>
</body>
</html>