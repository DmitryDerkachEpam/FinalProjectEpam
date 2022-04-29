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
			<c:forEach var="item" items="${sessionScope.itemsForBoughtOrder}">
			<label>
				Medicine name: ${item.associatedMedicine.name}<br> Quantity:
				${item.quantity}<br>  
				Price per medicine: ${item.associatedMedicine.price}$<br><br>
				<c:if test="${not empty sessionScope.messageFromReceiptService}">
				<c:if test="${sessionScope.medId == item.associatedMedicine.id}">
					<div style="color: blue;">${sessionScope.messageFromReceiptService}</div>
					${sessionScope.remove('messageFromReceiptService')}
					${sessionScope.remove('medId')}
				</c:if>
			</c:if>
			</label>
			</c:forEach>
			Total price: ${sessionScope.orderTotalPrice}$<br>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>