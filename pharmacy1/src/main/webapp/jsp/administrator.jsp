<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Doctor's page</title>
   <!--  <link rel="stylesheet" href="css/stylesAdminUser.css"> -->
   <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/css/stylesAdminUser.css" />
</head>
<body>
 <%@ include file="header.jsp"%>
<div id="wrapper">
    <div class="main">
        <div id="sidebar">
            <ul>
               	<li>
					<form action="${pageContext.request.contextPath}/mainController" method="post">
						<button class='btn' type="submit" name="command" value="showallusers">Users</button>
						<br>
					</form>
				</li>
            </ul>
        </div>
        <div id="content">
<c:forEach var="user" items="${sessionScope.users}">
	<form action="${pageContext.request.contextPath}/mainController" method="post">
	<input type="hidden" name="userId" value="${user.id}">
        <label>
        
		User name: ${user.name}<br>
		User email : ${user.email} <br> 
		User role: ${user.userRole}<br>

		Change role to:
		<select name="role" id="roleId">
            <c:forEach var="role" items="${sessionScope.roles}">
            <%-- 	<input type="hidden" name="roleValue" value="${role}"> --%>
                <option value="${role}">${role}</option>
            </c:forEach>
        </select>
        
        <button type="submit" name="command" value="changeRole">Change user role</button> 
          
    

		
		</label>
	</form>
</c:forEach>


        </div>
    </div>
</div>
 <%@ include file="footer.jsp"%>
</body>
</html>
