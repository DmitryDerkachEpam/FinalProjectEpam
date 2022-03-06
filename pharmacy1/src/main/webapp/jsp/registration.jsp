<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>registration</title>
    <link rel="stylesheet" type="text/css" href="css/stylesLoginRegistration.css">
</head>
<body>
<%@ include file="header.jsp"%>
<div></div>
<form action="${pageContext.request.contextPath}/mainController" method="post">
<input type="hidden" name="command" value="registration"/>
    <div id="loginWrapper">
        <div id="loginLabel">Please, fill the registration form:</div><br>
        <div>
            <div>
                User name
            </div>
            <label for="userName">
                <input id="userName" type="text" name="name"><br><br>
            </label>
            <div>
                Email
            </div>
            <label for="name">
                <input id="name" type="email" name="email"><br><br>
            </label>
            <div>
                Password
            </div>
            <label for="password">
                <input id="password" type="password" name="password"><br><br>
            </label>
            <input id="submitButton" type="submit" value="Submit">
            <!--Отредактироваит CSS -->
            <input id="submitButton" type="reset" value="Reset">
        </div>
        <br>
        <div>
            <a href="../jsp/login.jsp">Or login to your account</a>
        </div>
    </div>
</form>
<%@ include file="footer.jsp"%>
</body>
</html>