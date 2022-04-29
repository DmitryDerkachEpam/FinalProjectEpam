<%@page import="com.epam.entity.receipt_state.ReceiptState"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
					<li>
						<form action="${pageContext.request.contextPath}/mainController"
							method="post">
							<button class='btn' type="submit" name="command"
								value="showallmedicines">Medicine List</button>
							<br>
						</form>
					</li>
					<li>
						<form action="${pageContext.request.contextPath}/mainController"
							method="post">
							<button class='btn' type="submit" name="command"
								value="showuserreceipts">Receipts status</button>
							<br>
						</form>
					</li>
					<li>
						<form action="${pageContext.request.contextPath}/mainController"
							method="post">
							<button class='btn' type="submit" name="command"
								value="showusercart">Shopping cart</button>
							<br>
						</form>
					</li>
					<li>
						<form action="${pageContext.request.contextPath}/mainController"
							method="post">
							<button class='btn' type="submit" name="command"
								value=showorderhistory>Order history</button>
							<br>
						</form>
					</li>
				</ul>
			</div>
			<div id="content">
    <c:forEach var="receipts" items="${sessionScope.receiptDtos}">
    	<form action="${pageContext.request.contextPath}/mainController" method="post">
			<input type="hidden" name="receiptDtoId" value="${receipts.id}" />
            <label>
            Receipt for medicine: ${receipts.medicineName}<br>
            Receipt state: ${receipts.receiptState}
			<c:choose>
			<c:when test="${receipts.receiptState eq 'APPROVED'}">
			<button type="submit" name="command" value="requestreceiptprolongation">Request prolongation</button>
			</c:when>
<%-- 			<c:otherwise>
            No comment sir...
        	</c:otherwise> --%>
			</c:choose>
			
			<br>
            <c:if test="${not empty receipts.expirationDate}">
				Expiration date: ${receipts.expirationDate}<br>
			</c:if>
			
			<c:if test="${empty receipts.expirationDate}">
				Expiration date: Uncertain<br>
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