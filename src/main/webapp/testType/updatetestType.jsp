<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "it.contrader.dto.TestTypeDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>TestType Edit</title>
</head>
<body>

<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <a href="homeadmin.jsp">Home</a>
  <a href="UserServlet?mode=userlist">Users</a>
  <a href="ProgettiServlet?mode=progettilist">Projects</a>
  <a href="SupportServlet?mode=supportlist">Support</a>
  <a class="active" href="TestTypeServlet?mode=testTypelist">TestType</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<br>
<div class="main">
<%TestTypeDTO u = (TestTypeDTO) request.getAttribute("dto");%>


<form id="floatleft" action="TestTypeServlet?mode=update&id=<%=u.getId()%>" method="post">
  <div class="row">
    <div class="col-25">
      <label for="type_t">Type_t</label>
    </div>
    <div class="col-75">
      <input type="text" id="type_t" name="type_t" value=<%=u.getType_t()%>>
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="descrizione">Descrizione</label>
    </div>
    <div class="col-75">
      <input
			type="text" id="descrizione" name="descrizione" value=<%=u.getDescrizione()%>> 
    </div>
    </div>
      <button type="submit" >Edit</button>
</form>	
</div>
<br>
<%@ include file="../css/footer.jsp" %>	
</body>
</html>