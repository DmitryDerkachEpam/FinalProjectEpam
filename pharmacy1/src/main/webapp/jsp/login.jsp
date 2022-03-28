<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>login</title>
    <link rel="stylesheet" type="text/css" href="css/stylesLoginRegistration.css">
</head>
<body>
<%@ include file="header.jsp"%>
<div></div>
<form action="${pageContext.request.contextPath}/mainController" method="post">
<input type="hidden" name="command" value="login"/>
    <div id="loginWrapper">
        <div id="loginLabel">Welcome to Pharmacy ink!</div>
        <div>
            <c:if test="${not empty sessionScope.message}">
                <div style="color: red">${sessionScope.message} </div>
                ${sessionScope.remove('message')}
            </c:if>
            <div>
                Email
            </div>
            <label for="name">
                <input id="name" type="text" name="email">
            </label>
            <br><br>
            <div>
                Password
            </div>
            <label for="password">
                <input id="password" type="password" name="password">
            </label>
            <br><br>
            <input id="submitButton" type="submit" value="Submit">
        </div>
        <br>
        <div>
            <a href="../jsp/registration.jsp">Or create a new account</a>
        </div>
    </div>
</form>
<%@ include file="footer.jsp"%>
</body>
</html>