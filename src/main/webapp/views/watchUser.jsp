<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="watchUser" scope="session" type="model.User"/>
<jsp:useBean id="groupIndex" scope="session" type="java.lang.Integer"/>
<html>
<head>
    <title>Full Focus</title>
    <link rel="stylesheet" href="../styles/header.css">
    <link rel="stylesheet" href="../styles/profile.css">
</head>
<body>
<div class="header">
    <h1>Full Focus
        <a methods="post" href="/list/">To Do</a>
        <form action="/logout/" title="logout">
            <button>Logout</button>
        </form>
    </h1>
    <h2>Get your things done...</h2>
</div>

<div class="main">
    <h2 style="font-size: 45px; margin-left: 5%; font-weight: bold">${watchUser.username}<span style="font-weight: normal">'s to do list</span></h2>
    <table style="width: 100%; margin-left: 0; margin-right: 0; margin-bottom: 0; margin-top: -20px">
        <tr>
            <th style="width: 50%">
                <h1 style="text-align: left">Groups</h1>
            </th>
            <th style="width: 50%">
                <h1 style="text-align: left">Tasks</h1>
            </th>
        </tr>
        <tr style="margin-top: -10px">
            <td style="width: 50%">
                <ul style="overflow: auto; height: 450px; max-height: 450px; text-align: left">
                    <c:if test="${watchUser.groups.size() > 0}">
                        <c:forEach var = "i" begin = "0" end = "${watchUser.groups.size() - 1}">
                            <form action="/watchUser/" method="get">
                                <input type="hidden" name="groupIndex" value="${i}">
                                <input type="hidden" name="watchUsername" value="${watchUser.username}">
                                <button type="submit" style="border: 0; margin-left: 20px; height: 35px; max-height: 30px; font-weight: normal; color: ${groupIndex.equals(i) ? "orange" : "#333"}; font-size: 30px">${watchUser.groups.get(i).name}</button>
                            </form>
                        </c:forEach>
                    </c:if>
                    <c:if test="${watchUser.groups.size() == 0}">
                        <h4 style="margin-top: 100px; font-family: 'Avenir Next'; font-weight: normal; color: gray; font-size: 25px; text-align: center">No groups.</h4>
                    </c:if>
                </ul>
            </td>
            <td style="width:50%; margin-top: -5px">
                <ol style="overflow: auto; height: 450px; max-height: 450px; text-align: left">
                    <c:if test="${watchUser.groups.get(groupIndex).tasks.size() > 0}">
                        <c:forEach var = "i" begin = "0" end = "${watchUser.groups.get(groupIndex).tasks.size() - 1}">
                            <li style="font-family: 'Avenir Next'; font-weight: normal; font-size: 30px; color: #333; height: 40px; max-height: 40px;">${watchUser.groups.get(groupIndex).tasks.get(i).text}</li>
                        </c:forEach>
                    </c:if>
                    <c:if test="${watchUser.groups.get(groupIndex).tasks.size() == 0}">
                        <h4 style="margin-top: 100px; font-family: 'Avenir Next'; font-weight: normal; color: gray; font-size: 25px; text-align: center">No tasks.</h4>
                    </c:if>
                </ol>
            </td>
        </tr>
    </table>
</div>
</body>
</html>