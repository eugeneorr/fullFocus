<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="mainFocus" scope="session" type="java.lang.String"/>
<jsp:useBean id="groupIndex" scope="session" type="java.lang.Integer"/>
<jsp:useBean id="user" scope="session" type="model.User"/>
<html>
<head>
    <title>Full Focus</title>
    <link rel="stylesheet" href="../styles/header.css">
    <link rel="stylesheet" href="../styles/list.css">
</head>
<body>
    <div class="header">
        <h1>Full Focus
            <a href="/profile/">${user.username}</a>
            <form action="/logout/" title="logout">
                <button>Logout</button>
            </form>
        </h1>
        <h2>${mainFocus.equals("") ? "Get your things done..." : "Main focus: "} <span> ${mainFocus}</span></h2>
    </div>

    <div class="main">
        <table style="width: 100%; margin: 0">
            <tr>
                <th style="width: 20%">
                    <h1>Groups</h1>
                </th>
                <th style="width: 5%">
                </th>
                <th style="width: 50%">
                    <h1>Tasks</h1>
                </th>
                <th style="width: 12.5%">
                    <c:if test="${user.groups.size() > 0}">
                        <form action="/addTask/" method="get">
                            <input type="hidden" name="newTaskGroupIndex" value="${groupIndex}">
                            <button style="; margin-right: 10%; font-size: 20px; border: 0; font-family: 'Avenir Next'; font-weight: normal; color: #333">+ New Task</button>
                        </form>
                    </c:if>
                </th>
                <th style="width: 12.5%">
                </th>
            </tr>
            <tr style="margin-top: -10px">
                <td style="width: 20%">
                    <ul style="overflow: auto; height: 460px; max-height: 460px; text-align: left; vertical-align: top;">
                        <c:forEach var = "i" begin = "0" end = "${user.groups.size() > 0 ? user.groups.size() - 1 : 0}">
                            <c:if test="${user.groups.size() > 0}">
                                <form action="/list/" method="post">
                                    <input type="hidden" name="groupIndex" value="${i}">
                                    <button type="submit" style="border: 0; font-family: 'Avenir Next'; font-weight: normal; font-size: 25px; height: 30px; max-height: 30px; color: ${groupIndex.equals(i) ? "orange" : "#333"}">
                                        <c:out value = "${user.groups.get(i).name}"/>
                                    </button>
                                </form>
                            </c:if>
                        </c:forEach>
                    </ul>
                </td>
                <td style="width: 5%">
                    <ul style="overflow: auto; height: 460px; max-height: 460px; text-align: left; vertical-align: top;">
                        <c:forEach var = "i" begin = "0" end = "${user.groups.size() > 0 ? user.groups.size() - 1 : 0}">
                            <c:if test="${user.groups.size() > 0}">
                                <form action="/remove/" method="post">
                                    <input type="hidden" name="username" value="${user.username}">
                                    <input type="hidden" name="groupIndex" value="${i}">
                                    <button type="submit" style="border: 0; padding-right: 50px; height: 30px; max-height: 30px; color: #333; font-weight: lighter; font-size: 25px">X</button>
                                </form>
                            </c:if>
                        </c:forEach>
                    </ul>
                </td>
                <td style="width:50%; margin-top: -5px">
                    <ol style="overflow: auto; height: 460px; max-height: 460px; text-align: left; vertical-align: top;">
                        <c:forEach var = "i" begin = "0" end = "${(user.groups.size() > 0 && user.groups.get(groupIndex).tasks.size() > 0) ? (user.groups.get(groupIndex).tasks.size() - 1) : 0}">
                            <c:if test="${user.groups.size() > 0 && user.groups.get(groupIndex).tasks.size() > 0}">
                                <li style="border: 0; padding-left: 20px; font-family: 'Avenir Next'; font-weight: normal; font-size: 30px;">
                                    <c:out value = "${user.groups.get(groupIndex).tasks.get(i).text}"/>
                                </li>
                            </c:if>
                            <c:if test="${user.groups.size() > 0 && user.groups.get(groupIndex).tasks.size() == 0}">
                                <form action="/addTask/" method="get">
                                    <input type="hidden" name="newTaskGroupIndex" value="${groupIndex}">
                                    <button style="; margin-right: 10%; font-size: 20px; border: 0; font-family: 'Avenir Next'; font-weight: normal; color: #333">You haven't tasks in this group yet.</button>
                                </form>
                            </c:if>
                        </c:forEach>
                    </ol>
                </td>
                <td style="width: 10%; height: 460px; max-height: 460px; text-align: left; vertical-align: top;">
                    <c:forEach var = "i" begin = "0" end = "${(user.groups.size() > 0 && user.groups.get(groupIndex).tasks.size() > 0) ? (user.groups.get(groupIndex).tasks.size() - 1) : 0}">
                        <c:if test="${user.groups.size() > 0 && user.groups.get(groupIndex).tasks.size() > 0}">
                            <form action="/addTask/" method="get">
                                <input type="hidden" name="newTaskGroupIndex" value="${groupIndex}">
                                <input type="hidden" name="taskText" value="${user.groups.get(groupIndex).tasks.get(i).text}">
                                <input type="hidden" name="taskIndex" value="${i}">
                                <button type="submit" style="border: 0; color: #333; font-weight: lighter; font-size: 25px">Edit</button>
                            </form>
                        </c:if>
                    </c:forEach>
                </td>
                <td style="width: 10%; height: 460px; max-height: 460px; text-align: left; vertical-align: top;">
                    <c:forEach var = "i" begin = "0" end = "${(user.groups.size() > 0 && user.groups.get(groupIndex).tasks.size() > 0) ? (user.groups.get(groupIndex).tasks.size() - 1) : 0}">
                        <c:if test="${user.groups.size() > 0 && user.groups.get(groupIndex).tasks.size() > 0}">
                            <form action="/remove/" method="post">
                                <input type="hidden" name="username" value="${user.username}">
                                <input type="hidden" name="groupIndex" value="${groupIndex}">
                                <input type="hidden" name="taskIndex" value="${i}">
                                <button type="submit" style="border: 0; color: #333; font-weight: lighter; font-size: 25px">X</button>
                            </form>
                        </c:if>
                    </c:forEach>
                </td>
            </tr>
        </table>
        <a style="font-size: 20px;text-decoration: none; color: #333; font-family: 'Avenir Next'; font-weight: normal;margin-left: 5%" href="/addGroup/">+ Add group</a>
    </div>
</body>
</html>