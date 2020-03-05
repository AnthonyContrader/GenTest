<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin</title>
<link href="css/vittoriostyle.css" rel="stylesheet">
</head>
<body>
<%@include file="css/header.jsp"%>


<div class="navbar">
  <a class="active" href="homeadmin.jsp">Home</a>
  <a href="UserServlet?mode=userlist">Users</a>
  <a href="ProgettiServlet?mode=progettilist">Projects</a>
  <a href="SupportServlet?mode=supportlist">Support</a>
  <a href="CodesServlet?mode=codeslist">Codes</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>

<div class="main">
	<div align = "center">
		<h1>Welcome ${user.getUsername()}</h1>

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	</div>
</div>


<%@ include file="css/footer.jsp" %>

</body>
</html>