<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<br>
<br>


<form action="${pageContext.request.contextPath}/mainController" method="post">
    <label for="emailId"><fmt:message key="user.email" />
    	<input type="hidden" name="command" value="login"/>
        <input type="text" name="email" id="emailId">
    </label>
    <br>
    <br>
    <label for="passwordId"><fmt:message key="user.password" />
        <input type="password" name="password" id="passwordId">
    </label>
    <br>
    <br>
    <button type="reset"><fmt:message key="button.reset" /></button>
    <button type="submit"><fmt:message key="button.submit" /></button>
    <br>
    <c:if test="${param.error !=null}">
        <div style="color: red">
            <span><fmt:message key="error" /></span>
        </div>
    </c:if>
</form>
<h3>
    <a href="http://localhost:8080/jsp/registration.jsp"><fmt:message key="message.first.time"/></a>
</h3>
</body>
</html>