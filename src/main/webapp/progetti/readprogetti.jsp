<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = " it.contrader.dto.ProgettiDTO "%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Read Progetti</title>
</head>
<body>


<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <a href="homeadmin.jsp">Home</a>
  <a class="active"  href="ProgettiServlet?mode=progettilist">Progetti</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<br>

<div class="main">

<%ProgettiDTO u = (ProgettiDTO) request.getAttribute("dto");%>


<table>
	<tr> 
		<th>Nome del progetto</th>
		<th>Data di inserimento</th>
		<th>Data di modifica</th>
	</tr>
	<tr>
		<td><%=u.getNome()%></td>
		<td> <%=u.getData_i()%></td>
		<td> <%=u.getData_m()%></td>
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

</body>
</html>