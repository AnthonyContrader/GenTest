<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"
         import="it.contrader.dto.CodesDTO"%>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.FileReader" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Codes Read">
    <link href="/css/vittoriostyle.css" rel="stylesheet">
    <title>Read User</title>
</head>
<body>
<%@ include file="./css/header.jsp"%>
<div class="navbar">
    <a href="/homeadmin.jsp">Home</a>
    <a href="/user/getall">User</a>
    <a class="active" href="/codes/getall">Codes</a>
    <a href="/user/logout" id="logout">Logout</a>
</div>
<br>

<div class="main">
    <%
        CodesDTO u = (CodesDTO) request.getSession().getAttribute("dto");
    %>


    <table>
        <tr>
            <th>Nome del codice</th>
            <th>Data di inserimento</th>
            <th>Data di modifica</th>
            <th>Tipo del test</th>
        </tr>
        <tr>
            <td><%=u.getNome()%></td>
            <td><%=u.getData_i()%></td>
            <td><%=u.getData_m()%></td>
            <td><%=u.getType_t()%></td>
        </tr>
    </table>

    <br> <br> <br> <br> <br> <br> <br>
    <br> <br> <br> <br> <br> <br> <br>
    <%

        String cuser= request.getSession().getAttribute("user").toString();
        BufferedReader in = new BufferedReader(new FileReader("/Users/samirhysa/eclipse/jee-2019-12/apache-tomcat-9.0.31/webapps/codes/" +cuser+"/"+u.getNome()+".java"));
        String str=null;
        while((str=in.readLine()) != null){ %>
    <% out.println(str); %>  <br>

    <%}
        in.close(); %>

</div>

<%@ include file="./css/footer.jsp"%>
</body>
</html>