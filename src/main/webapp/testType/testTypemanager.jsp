<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" 
	import="java.util.List"
	import="it.contrader.dto.TestTypeDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>TestType Manager</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>

<div class="navbar">
  <a  href="homeadmin.jsp">Home</a>
  <a   href="UserServlet?mode=userlist">Users</a>
  <a   href="ProgettiServlet?mode=progettilist">Projects</a>
  <a  href="SupportServlet?mode=supportlist">Support</a>
  <a class="active" href="TestTypeServlet?mode=testTypelist">TestType</a>
  <a href="LogoutServlet" id="logout">Logout</a>
</div>
<div class="main">
	<%
		List<TestTypeDTO> list = (List<TestTypeDTO>) request.getAttribute("list");
	%>

<br>

	<table>
		<tr>
			<th>Type_t</th>
			<th>Descrizione</th>
			
			<th></th>
			<th></th>
		</tr>
		<%
			for (TestTypeDTO u : list) {
		%>
		<tr>
			<td><a href=TestTypeServlet?mode=read&id=<%=u.getId()%>>
					<%=u.getType_t()%>
			</a></td>
			<td><%=u.getDescrizione()%></td>
		
			<td><a href=TestTypeServlet?mode=read&update=true&id=<%=u.getId()%>>Edit</a>
			</td>
			<td><a href=TestTypeServlet?mode=delete&id=<%=u.getId()%>>Delete</a>
			</td>

		</tr>
		<%
			}
		%>
	</table>



<form id="floatright" action="TestTypeServlet?mode=insert" method="post">
  <div class="row">
    <div class="col-25">
      <label for="type_t">Type_t</label>
    </div>
    <div class="col-75">
      <input type="text" id="type_t" name="type_t" placeholder="inserisci type_t">
    </div>
  </div>
  <div class="row">
    <div class="col-25">
     <label for="descrizione">Descrizione</label>
    </div>
    <div class="col-75">
      <input type="text" id="descrizione" name="descrizione" placeholder="inserisci descrizione"> 
    </div>
  </div>
      <button type="submit" >Insert</button>
</form>

</div>
<br>
<%@ include file="../css/footer.jsp" %>
</body>
</html>

