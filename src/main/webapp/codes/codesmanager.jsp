<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" 
	import="java.util.List"
	import="it.contrader.dto.CodesDTO"
	import="it.contrader.dto.UserDTO"%>
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
	<a href="homeadmin.jsp">Home</a>
	<a href="UserServlet?mode=userlist">Users</a>
	<a href="ProgettiServlet?mode=progettilist">Projects</a>
	<a href="SupportServlet?mode=supportlist">Support</a>
	<a class = "active" href="CodesServlet?mode=codeslist">Codes</a>
	<a href="CodesServlet?mode=testlist">Test</a>
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
			<th>Tipologia del test</th>
			
			<th></th>
			<th></th>
		</tr>
		<%
			for (CodesDTO u : list) {
		%>
		<tr>
			<td><a href=CodesServlet?mode=read&id=<%=u.getId()%>>
					<%=u.getNome()%>
			</a></td>
			<td><%=u.getData_i()%></td>
			<td><%=u.getData_m()%></td>
			<td><%=u.getType_t() %></td>
			<td><a href=CodesServlet?mode=read&update=true&id=<%=u.getId()%>>Edit</a>
			</td>
			<td><a href="CodesServlet?mode=delete&id=<%=u.getId()%>&nome=<%=u.getNome()%>">Delete</a>
			</td>
			<td><a href="TestServlet?mode=generate&id=<%=u.getId()%>&nome=<%=u.getNome()%>&type_t=<%=u.getType_t()%>">Generate test</a>
			</td>

		</tr>
		<%
			}
		%>
	</table>

<form id="floatright" action="CodesServlet?mode=insert"  method="post" enctype = "multipart/form-data" >
  <div class="row">
    <div class="col-75">

      	<input type="text" id="nome" name="nome" placeholder="codes name">

		<input type = "file" name = "file" size = "50"/>  
		<div class="row">
    <div class="col-25">

    </div>
   		 <div class="col-75">
 			<select id="type_t" name="type_t">
  				<option value="test1">Test1</option>
  				<option value="test2">Test2</option>
 
			</select>
    	</div>
  </div> 
   </div>
  </div>
      <button type="submit" >upload</button>
</form>
		
</div>
<br>
<%@ include file="../css/footer.jsp" %>
</body>

</html>
