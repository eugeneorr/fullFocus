<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="username" scope="session" type="java.lang.String"/>
<html>
<head>
    <title>Full Focus</title>
    <link rel="stylesheet" href="../styles/header.css">
    <link rel="stylesheet" href="../styles/mainTask.css">

</head>
<body>
<div class="header">
    <h1>Full Focus
        <form action="/logout/">
            <button>Logout</button>
        </form>
    </h1>
    <h2>Get your things done...</h2>
</div>

<main>
    <div class="main">
        <h1>Good afternoon, ${username}!</h1>
        <h2>What is your main focus for today?</h2>
        <div class="mainTask-form">
            <form action="/list/" method="POST">
                <input type="text" name="mainTask">
                <button style="color: white; font-size: 17px; font-family: 'Avenir Next'; font-weight: normal; border-radius: 25px; margin-top: 35px; height: 50px; width: 125px; text-align: center; margin-left: 162.5px;  color: white; background-color: #333">Continue</button>
            </form>
        </div>
    </div>
</main>
</body>
</html>