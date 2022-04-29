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
               	<li>
					<form action="${pageContext.request.contextPath}/mainController"
						method="post">
						<button class='btn' type="submit" name="command" value="showallreceipts">Receipts</button>
						<br>
					</form>
				</li>
            </ul>
        </div>
        <div id="content">
<c:forEach var="receipts" items="${sessionScope.receipts}">
	<form action="${pageContext.request.contextPath}/mainController" method="post">
        <label>
        
		Medicine name: ${receipts.medicineName}<br>
		Requested by: ${receipts.userName} <br> 
		Receipt state: ${receipts.receiptState}<br>

		<c:if test="${not empty sessionScope.message}">
			<c:if test="${sessionScope.changedReceipt == receipts.id}">
				<div style="color: green;">${sessionScope.message}</div>
			 	${sessionScope.remove('message')}
			</c:if>
		</c:if>
		
		<input type="hidden" name="receiptId" value="${receipts.id}">
			
		<c:if test="${receipts.receiptState eq 'REQUESTED'}">
			<button type="submit" name="command" value="approvereceipt">Approve receipt</button>
		</c:if>
		
		</label>
	</form>
</c:forEach>


        </div>
    </div>
</div>
 <%@ include file="footer.jsp"%>
</body>
</html>
