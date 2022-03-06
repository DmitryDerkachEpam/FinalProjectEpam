<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Doctor's page</title>
    <link rel="stylesheet" href="css/stylesAdminUser.css">
</head>
<body>
 <%@ include file="header.jsp"%>
<div id="wrapper">
    <div class="main">
        <div id="sidebar">
            <ul>
                <li><a href="#">Information about receipts</a></li>
            </ul>
        </div>
        <div id="content">
<c:forEach var="receipts" items="${sessionScope.receipts}">
    <h3>
        <label>
		Medicine name: ${receipts.medicineName}</br>
		Requested by: ${receipts.userName} </br>
		Receipt state: ${receipts.receiptState}
		<c:if test="${receipts.receiptState eq 'REQUESTED'}">
			<input type="submit" value="Approve receipt">
		</c:if>
	
	

        </label>
    </h3>
</c:forEach>


        </div>
    </div>
</div>
 <%@ include file="footer.jsp"%>
</body>
</html>
