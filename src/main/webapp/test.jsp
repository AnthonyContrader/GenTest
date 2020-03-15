<%@ page  import="java.util.*"%>
<%@ page import="it.contrader.dto.TestDTO" %>
<%@ page import="it.contrader.dto.CodesDTO" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Test Management">
    <link href="/css/vittoriostyle.css" rel="stylesheet">
    <title>Test Manager</title>

</head>
<body>
<%@ include file="./css/header.jsp"%>

<div class="navbar">
    <a href="/homeadmin.jsp">Home</a>
    <a href="/user/getall">User</a>
    <a class="active" href="/test/getall">Test</a>
    <a href="/user/logout" id="logout">Logout</a>
</div>
<div class="main">
    <%
        List<TestDTO> list = (List<TestDTO>) request.getSession().getAttribute("list");
        List<CodesDTO> listo = (List<CodesDTO>) request.getSession().getAttribute("listos");
    %>

    <br>

    <table>
        <tr>
            <th>nome del codice</th>
            <th>Data inserimento</th>
            <th>Data modifica</th>
            <th>Tipo del test</th>
            <th>Nome del relativo codice</th>
            <th></th>
        </tr>
        <%
            for (TestDTO u : list) {
        %>
        <tr>
            <td><a href="/test/read?id=<%=u.getId()%>">
                <%=u.getNome()%>
            </a></td>
            <td><%=u.getData_i()%></td>
            <td><%=u.getData_m()%></td>
            <td><%=u.getType_t()%></td>
            <td><%=u.getCodes().getNome()%></td>

            <td><a href="/test/delete?id=<%=u.getId()%>&nome=<%=u.getNome()%>">Delete</a></td>

        </tr>
        <%
            }
        %>
    </table>
    <form id="floatright" action="/test/insert" method="post" >
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

        <select id="codes" name="Codesname" required>
            <option value="" disabled selected>Choose your option</option>
            <%
                for (CodesDTO o : listo) {
            %>
            <option value="<%=o.getId()%>"><%=o.getNome()%></option>
            <%
                }
            %>

            </select>
        </div>
        <button type="submit">Insert</button>
    </form>

</div>

</div>
<br>
<%@ include file="./css/footer.jsp"%>
</body>
</html>