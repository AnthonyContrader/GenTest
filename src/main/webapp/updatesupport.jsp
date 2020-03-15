<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.SupportDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Support Management">
<meta name="author" content="Vittorio Valent">
<link href="/css/vittoriostyle.css" rel="stylesheet">
<title>Edit Support</title>
</head>
<body>
<%@ include file="./css/header.jsp" %>
	<div class = "navbar">
		<a href="/homeadmin.jsp">Home</a>
		<a href="/user/getall">Users</a>
		<a href="/progetti/getall">Progetti</a>
		<a class="active" href="/support/getall">Support</a>
		<a href="/user/logout" id ="logout">Logout</a>
	</div>
	<br>
	<div class="main">
	<%SupportDTO u=(SupportDTO) request.getSession().getAttribute("dto"); %>
	
	<form id="floatleft" action="/support/update" method="post">
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
 	<input type="text" id="risposta" name="risposta" value=<%=u.getRisposta()%>>
 	</div>
    	<input type="hidden" name="id" value =<%=u.getId() %>>
  </div>
  
      <button type="submit" >Edit</button>
</form>
	
	</div>
	<br>
	<%@ include file="./css/footer.jsp" %>
</body>
</html>