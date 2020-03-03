<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.SupportDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Edit Q&A</title>
</head>
<body>

<%@ include file="../css/header.jsp" %>
<div class="navbar">
	<a href="homeadmin.jsp">Home</a>
	<a class ="active" href="UserServlet?mode=userlist">Users</a>
	<a class="active"href=ProgettiServlet?mode=progettilist> Projects</a>
	<a class="active" href=SupportServlet?mode=supportlist> Support</a>
	<a href="LogoutServlet" id="Logout">Logout</a>
</div>
<br>
<div class="main">

<%SupportDTO u = (SupportDTO)  request.getAttribute("dto");%>

<form id="floatleft" action="SupportServlet?mode=update&id=<%=u.getId()%>" method="post">

  <div class="row">
    <div class="col-25">
      <label for="domanda">Domanda</label>
    </div>
    <div class="col-75">
      <input type="text" id="domanda" name="domanda" value=<%=u.getDomanda()%>>
    </div>
  </div>
  
  <div class="row">
    <div class="col-25">
     <label for="risposta">Risposta</label>
    </div>
    <div class="col-75">
      <input
			type="text" id="risposta" name="risposta" value=<%=u.getRisposta()%>> 
    </div>
  </div>
    <button type="submit" >Edit</button>
      </form>>
	</div>
<br>
<%@ include file="../css/footer.jsp" %>	
</body>
</html>