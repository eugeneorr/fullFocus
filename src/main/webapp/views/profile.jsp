<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" scope="session" type="model.User"/>
<jsp:useBean id="sharedUsers" scope="session" type="java.util.HashSet<java.lang.String>"/>
<jsp:useBean id="usersToWatch" scope="session" type="java.util.ArrayList<model.User>"/>
<html>
<head>
    <title>Full Focus</title>
    <link rel="stylesheet" href="../styles/header.css">
    <link rel="stylesheet" href="../styles/profile.css">
</head>
<body>
<div class="header">
    <h1>Full Focus
        <a href="/list/">To Do</a>
        <form action="/logout/" title="logout">
            <button>Logout</button>
        </form>
    </h1>
    <h2>Get your things done...</h2>
</div>

<div class="main">
    <h2>Good afternoon, ${user.username}!</h2>
    <table style="width: 100%; margin-left: 0; margin-right: 0; margin-bottom: 0; margin-top: -20px">
        <tr>
            <th style="width: 50%">
                <h1>Access to observe your list for users:</h1>
            </th>
            <th style="width: 50%">
                <h1>Watch users lists:</h1>
            </th>
        </tr>
        <tr style="margin-top: -10px">
            <td style="width: 50%">
                <ul style="overflow: auto; height: 350px; max-height: 350px; text-align: left">
                    <c:if test="${sharedUsers.size() > 0}">
                        <c:forEach items="${sharedUsers}" var="current">
                            <h3 style="height: 30px">${current}
                                <span>
                                    <form style="display: inline-block" action="/remove/" method="post">
                                        <input type="hidden" name="sharedListUsername" value="${current}">
                                        <input type="hidden" name="username" value="${user.username}">
                                        <button type="submit" style="border: 0; margin-left: 20px; height: 30px; max-height: 30px; color: #333; font-weight: lighter; font-size: 25px">X</button>
                                    </form>
                                </span>
                            </h3>
                        </c:forEach>
                    </c:if>
                    <c:if test="${sharedUsers.size() == 0}">
                        <h4 style="margin-top: 100px; font-family: 'Avenir Next'; font-weight: lighter; color: gray; font-size: 25px; text-align: center">Your shared list is empty.</h4>
                    </c:if>
                </ul>
            </td>
            <td style="width:50%; margin-top: -5px">
                <ul style="overflow: auto; height: 350px; max-height: 350px; text-align: center">
                    <c:if test="${usersToWatch.size() > 0}">
                        <c:forEach var = "i" begin = "0" end = "${usersToWatch.size() - 1}">
                            <form action="/watchUser/" method="GET">
                                <input type="hidden" name="watchUsername" value="${usersToWatch.get(i).username}">
                                <button type="submit" style="border: 0; font-family: 'Avenir Next'; font-weight: normal; font-size: 30px; height: 30px; max-height: 30px; color: #333">
                                    <c:out value = "${usersToWatch.get(i).username}"/>
                                </button>
                            </form>
                        </c:forEach>
                    </c:if>
                    <c:if test="${usersToWatch.size() == 0}">
                        <h4 style="margin-top: 100px; font-family: 'Avenir Next'; font-weight: lighter; color: gray; font-size: 25px; text-align: center">Nobody shared tasks with you.</h4>
                    </c:if>
                </ul>
            </td>
        </tr>
    </table>
    <a style="font-size: 20px;text-decoration: none; color: #333; font-family: 'Avenir Next'; font-weight: normal;margin-left: 25%" href="/shareUser/">+ Share user</a>
</div>
</body>
</html>