<%@ page  import="java.util.*"%>
<%@ page import="it.contrader.dto.CodesDTO" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Codes Management">
    <link href="/css/vittoriostyle.css" rel="stylesheet">
    <title>Codes Manager</title>

</head>
<body>
<%@ include file="./css/header.jsp"%>

<div class="navbar">
    <a href="/homeadmin.jsp">Home</a>
    <a class="active" href="/codes/getall">Codes</a>
    <a href="/user/logout" id="logout">Logout</a>
</div>
<div class="main">
    <%
        List<CodesDTO> list = (List<CodesDTO>) request.getSession().getAttribute("list");
    %>

    <br>

    <table>
        <tr>
            <th>nome</th>
            <th>Data_i</th>
            <th>Data_m</th>
            <th></th>
            <th></th>
        </tr>
        <%
            for (CodesDTO u : list) {
        %>
        <tr>
            <td><a href="/codes/read?id=<%=u.getId()%>"> <%=u.getNome()%>
            </a></td>
            <td><%=u.getData_i()%></td>
            <td><%=u.getData_m()%></td>
            <td><%=u.getType_t()%></td>
            <td><%=u.getTest()%></td>
            <td><a href="/user/preupdate?id=<%=u.getId()%>">Edit</a></td>


            <td><a href="/user/delete?id=<%=u.getId()%>">Delete</a></td>

        </tr>
        <%
            }
        %>
    </table>



    <form id="floatright" action="/codes/insert" method="post">
        <div class="row">
            <div class="col-25">
                <label for="codes">Nome</label>
            </div>
            <div class="col-75">
                <input type="text" id="nome" name="nome"
                       placeholder="inserisci nome">
            </div>
        </div>

        <div class="row">
            <div class="col-25">
                <label for="type">test type</label>
            </div>
            <div class="col-75">
                <select id="type" name="type_t">
                    <option value="test 1">test1</option>
                    <option value="test 2">test2</option>

                </select>
            </div>
        </div>
        <button type="submit">Insert</button>
    </form>

</div>
<br>
<%@ include file="./css/footer.jsp"%>
</body>
</html>