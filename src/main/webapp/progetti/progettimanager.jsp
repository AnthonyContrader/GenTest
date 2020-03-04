<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="it.contrader.dto.ProgettiDTO"
    import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Project Manager</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>


<div class="navbar">
	<a href="homeadmin.jsp">Home</a>
	<a href="UserServlet?mode=userlist">Users</a>
	<a class="active" href="ProgettiServlet?mode=progettilist"> Projects </a>
	  <a href="SupportServlet?mode=supportlist">Support</a>
	<a href="LogoutServlet" id = "logout">Logout</a>
</div>
<div class= "main">

	<% 
		List<ProgettiDTO> list = (List<ProgettiDTO>) request.getAttribute("list");
	
	%>
<br>
	
	<table>
		<tr>
			<th>Nome del Progetto</th>
			<th>Nome data inserimento</th>
			<th>Nome data ultima modifica</th>
			<th></th>
			<th></th>
		</tr>
		<%
		
		 	for(ProgettiDTO u : list) {
		 		
		%>
		<tr>
			<td><a href=ProgettiServlet?mode=read&id=<%=u.getId() %>>
					<%=u.getNome() %>
			 </a></td>
			<td><%=u.getData_i() %></td>
			<td><%=u.getData_m() %></td>
			<td><a href=ProgettiServlet?mode=read&update=true&id=<%=u.getId()%>>Edit</a>
			</td>
			<td><a href=ProgettiServlet?mode=delete&id=<%=u.getId()%>>Delete</a>
			</td>

		</tr>
		<%
			}
		%>
	</table>
			

<form id="floatright" action="ProgettiServlet?mode=insert" enctype="multipart/form-data" method="post">
  <div class="row">
    <div class="col-25">
      <label for="progetti">Name of the project</label>
    </div>
    <div class="col-75">
      <input type="text" id="nome" name="nome" placeholder="project name">
    </div>
  </div>
      <button type="submit" >Insert</button>
</form>
</div>
<br>
<%@ include file="../css/footer.jsp" %>					
</body>
</html>