<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"
         import="java.util.List"
         import="it.contrader.dto.TestDTO"
        %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link href="../css/vittoriostyle.css" rel="stylesheet">
    <title>Test manager</title>
</head>
<body>
<%@ include file="../css/header.jsp" %>

<div class="navbar">
    <a href="homeadmin.jsp">Home</a>
    <a href="UserServlet?mode=userlist">Users</a>
    <a href="ProgettiServlet?mode=progettilist">Projects</a>
    <a href="SupportServlet?mode=supportlist">Support</a>
    <a href="CodesServlet?mode=codeslist">Codes</a>
    <a class = "active" href="CodesServlet?mode=testlist">Test</a>
    <a href="LogoutServlet" id="logout">Logout</a>
</div>
<div class="main">
    <%
        List<TestDTO> list = (List<TestDTO>) request.getAttribute("list");
    %>

    <br>

    <table>
        <tr>
            <th>Nome del test</th>
            <th>Data di inserimento</th>
            <th>Data di ultima modifica</th>
            <th>Tipologia del test</th>

            <th></th>
            <th></th>
        </tr>
        <%
            for (TestDTO u : list) {
        %>
        <tr>
            <td><a href=TestServlet?mode=read&id=<%=u.getId()%>>
                <%=u.getNome()%>
            </a></td>
            <td><%=u.getData_i()%></td>
            <td><%=u.getData_m()%></td>
            <td><%=u.getType_t() %></td>
            <td><a href="TestServlet?mode=deletet&id=<%=u.getId()%>&nome=<%=u.getNome()%>">Delete</a>
            </td>

        </tr>
        <%
            }
        %>
    </table>



</div>
<br>
<%@ include file="../css/footer.jsp" %>
</body>

</html>
