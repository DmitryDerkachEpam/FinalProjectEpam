<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <title>User's page</title>
    <link rel="stylesheet" href="css/stylesAdminUser.css">
</head>
<body>
 <%@ include file="header.jsp"%>
<div id="wrapper">
    <div class="main">
        <div id="sidebar">
            <ul>
                <li><a href="/jsp/user.jsp">Medicine list</a></li>
                <li><a href="/jsp/orderedReceipts.jsp">Ordered receipts</a></li>
                <li><a href="/jsp/shoppingCart.jsp">Shopping cart</a></li>
            </ul>
        </div>
        <div id="content">
<form action="${pageContext.request.contextPath}/mainController" method="post">
<input type="hidden" name="command" value="addreceiptrequest"/>
<c:forEach var="receipts" items="${sessionScope.receipts}">
    <h3>
        <label>
		Medicine name: ${receipts.medicineName}</br>
		Requested by: ${receipts.userName} </br>
		Receipt state: ${receipts.receiptState}
        </label>
    </h3>
</c:forEach>
</form>
        </div>
    </div>
</div>
<%@ include file="footer.jsp"%>
</body>
</html>