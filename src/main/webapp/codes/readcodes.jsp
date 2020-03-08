<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import ="it.contrader.dto.CodesDTO"%>
<%@ page import="java.io.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Read Codes</title>
</head>
<body>


<%@ include file="../css/header.jsp" %>
<div class="navbar">
	<a href="homeadmin.jsp">Home</a>
	<a href="UserServlet?mode=userlist">Users</a>
	<a href="ProgettiServlet?mode=progettilist">Projects</a>
	<a href="SupportServlet?mode=supportlist">Support</a>
	<a class = "active" href="CodesServlet?mode=codeslist">Codes</a>
	<a href="CodesServlet?mode=testlist">Test</a>
	<a href="LogoutServlet" id="logout">Logout</a>
</div>
<br>

<div class="main">
<%CodesDTO u = (CodesDTO) request.getAttribute("dto");%>


<table>
	<tr> 
		<th>Nome del codice</th>
		<th>Data di inserimento</th>
		<th>Data di ultima modifica</th>
		<th>Tipologia del test</th>
	</tr>
	<tr>
		<td> <%=u.getNome()%></td>
		<td> <%=u.getData_i()%></td>
		<td><%=u.getData_m()%></td>
		<td><%=u.getType_t()%></td>


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