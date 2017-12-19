<jsp:useBean id="newTaskGroupIndex" scope="session" type="java.lang.Integer"/>
<jsp:useBean id="taskIndex" scope="session" type="java.lang.Integer"/>
<jsp:useBean id="taskText" scope="session" type="java.lang.String"/>

<html>
<head>
    <meta charset="UTF-8">
    <title>Add Group</title>
    <link rel="stylesheet" href="../styles/header.css">
    <link rel="stylesheet" href="../styles/mainTask.css">
</head>
<body>
<div class="header">
    <h1>Full Focus</h1>
    <h2>Get your things done...</h2>
</div>

<main>
    <div class="main">
        <h1>Edit Task</h1>
        <h2>You can edit the task below</h2>
        <div class="mainTask-form">
            <form action="/list/" method="POST">
                <input type="text" name="editedTaskText" value="${taskText}">
                <input type="hidden" name="newTaskGroupIndex" value="${newTaskGroupIndex}">
                <input type="hidden" name="editedTaskIndex" value="${taskIndex}">
                <button style="color: white; font-size: 17px; font-family: 'Avenir Next'; font-weight: normal; border-radius: 25px; margin-top: 35px; height: 50px; width: 125px; text-align: center; margin-left: 162.5px;  color: white; background-color: #333">Continue</button>
            </form>
        </div>

    </div>
</main>
</body>
</html>