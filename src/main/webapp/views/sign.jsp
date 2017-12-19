<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="isSignUp" scope="session" type="java.lang.Boolean"/>
<jsp:useBean id="haveAccountText" scope="session" type="java.lang.String"/>
<jsp:useBean id="error" scope="session" type="java.lang.String"/>
<html>
<head>
    <title>Full Focus</title>
    <link rel="stylesheet" href="../styles/header.css">
    <link rel="stylesheet" href="../styles/sign.css">
    <style>

    </style>
</head>
<body>
<div class="header">
    <h1>Full Focus</h1>
    <h2>Get your things done...</h2>
</div>

<main>
    <div class="main">
        <h1>${isSignUp ? "Welcome!" : "Good afternoon!"}</h1>
        <h2 style="color: ${error.equals("") ? "#333" : "red"}">${isSignUp ? (error.equals("") ? "You need to sign up first." : error) : (error.equals("") ? "Happy to see you again!" : error)}</h2>
        <div class="sing-form">
            <form action="/mainTask/" method="POST">
                <h1>Username</h1>
                <input type="text" name="username">
                <h1>Password</h1>
                <input type="password" name="password">
                <input type="hidden" name="isSignUp" value="${isSignUp}">
                <button style="color: white; font-size: 17px; font-family: 'Avenir Next'; font-weight: normal; border-radius: 25px; margin-top: 35px; height: 50px; width: 125px; text-align: center; margin-left: 162.5px;  color: white; background-color: #333">${isSignUp ? "Sign Up" : "Sign In"}</button>
            </form>
            <h2 style="font-size: 22px"><c:out value="${haveAccountText}"/> <span><a style="text-decoration: none; color: red; font-weight: normal" href="${isSignUp ? "/signIn/" : "/signUp/"}">${isSignUp ? "Sign In" : "Sign Up"}</a></span></h2>
        </div>
    </div>
</main>
</body>
</html>