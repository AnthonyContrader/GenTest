<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.ProgettiDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Edit Progetti</title>
</head>
<body>

<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <a href="homeadmin.jsp">Home</a>
  <a  href="UserServlet?mode=userlist">Users</a>
  <a class="active"href=ProgettiServlet?mode=progettilist> Projects</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<br>
<div class="main">

<%ProgettiDTO u = (ProgettiDTO) request.getAttribute("dto");%>


<form id="floatleft" action="ProgettiServlet?mode=update&id=<%=u.getId()%>" method="post">

  <div class="row">
    <div class="col-25">
      <label for="user">Nome del Progetto</label>
    </div>
    <div class="col-75">
      <input type="text" id="nome" name="nome" value=<%=u.getNome()%>>
    </div>
  </div>
  
  <div class="row">
    <div class="col-25">
     <label for="pass">P</label>
    </div>
    <div class="col-75">
      <input
			type="text" id="data_i" name="data_i" value=<%=u.getData_i()%>> 
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="data_m">P</label>
    </div>
    <div class="col-75">
      <input
			type="text" id="data_m" name="data_m" value=<%=u.getData_m()%>> 
    </div>
  </div>
      <button type="submit" >Edit</button>
      </form>>
	</div>
<br>
<%@ include file="../css/footer.jsp" %>	

</body>
</html>