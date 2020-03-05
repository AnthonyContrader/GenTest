<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.TestTypeDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TestType read</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <a href="homeadmin.jsp">Home</a>
  <a href="UserServlet?mode=userlist">Users</a>
  <a  href="ProgettiServlet?mode=progettilist">Projects</a>
  <a  href="SupportServlet?mode=supportlist">Support</a>
  <a class="active"  href="UserServlet?mode=TestTypelist">TestType</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<br>

<div class="main">
<%TestTypeDTO  u = (TestTypeDTO) request.getAttribute("dto");%>


<table>
	<tr> 
		<th>Type_t</th>
		<th>Descrizione</th>
		
	</tr>
	<tr>
		<td><%=u.getType_t()%></td>
		<td> <%=u.getDescrizione()%></td>
		
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