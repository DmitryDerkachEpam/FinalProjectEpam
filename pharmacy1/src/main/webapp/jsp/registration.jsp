<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Regsitration Page!</title>
</head>
<body>
	Registration Page!
	<form action="${pageContext.request.contextPath}/mainController" method="post">
	<input type="hidden" name="command" value="registration"/>
	<button type="submit"><fmt:message key="button.submit" /></button>
	</form>
</body>
</html>