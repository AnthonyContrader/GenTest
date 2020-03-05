<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" 
	import="java.util.List"
	import="it.contrader.dto.CodesDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Codes manager</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>

<div class="navbar">
  <a  href="homeadmin.jsp">Home</a>
  <a class="active" href="UserServlet?mode=userlist">Users</a>
  <a  href="ProgettiServlet?mode=progettilist">Projects</a>
  <a href="SupportServlet?mode=supportlist">Support</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<div class="main">
	<%
		List<CodesDTO> list = (List<CodesDTO>) request.getAttribute("list");
	%>

<br>

	<table>
		<tr>
			<th>Nome del codice</th>
			<th>Data di inserimento</th>
			<th>Data di ultima modifica</th>
			<th>  </th>
			
			<th></th>
			<th></th>
		</tr>
		<%
			for (CodesDTO u : list) {
		%>
		<tr>
			<td><a href=UserServlet?mode=read&id=<%=u.getId()%>>
					<%=u.getNome()%>
			</a></td>
			<td><%=u.getData_i()%></td>
			<td><%=u.getData_m()%></td>
			<td><a href=CodesServlet?mode=read&update=true&id=<%=u.getId()%>>Edit</a>
			</td>
			<td><a href=CodesServlet?mode=delete&id=<%=u.getId()%>>Delete</a>
			</td>

		</tr>
		<%
			}
		%>
	</table>



<form id="floatright" action="CodesServlet?mode=insert"  method="post"  >
  <div class="row">
    <div class="col-25">
      <label for="codes">Name of Codes</label>
    </div>
    <div class="col-75">
      <input type="text" id="nome" name="nome" placeholder="codes name">
  </div>
  </div>
      <button type="submit" >Insert</button>
</form>

<form id="floatright" action="CodesServlet?mode=insertcode"  method="post" enctype = "multipart/form-data" >
  <div class="row">
    <div class="col-25">
      <label for="codes">upload you code</label>
    </div>
    <div class="col-75">
		<input type = "file" name = "file" size = "50"/>    </div>
  </div>
      <button type="submit" >upload</button>
</form>


</div>
<br>
<%@ include file="../css/footer.jsp" %>
</body>
</html>