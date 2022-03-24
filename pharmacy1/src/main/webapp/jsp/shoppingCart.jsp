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
				<c:forEach var="item" items="${sessionScope.items}">
					<form action="${pageContext.request.contextPath}/mainController"
						method="post">
						<input type="hidden" name="medicineId" value="${item.associatedMedicine.id}" /> 
							<label>
							Medicine name: ${item.associatedMedicine.name}<br> Quantity:
							${item.quantity}<br> 
							Dose:  ${item.associatedMedicine.dose} mg<br>
							Receipt required: <c:if
								test="${item.associatedMedicine.isReceiptRequired eq 'true'}">
								<span>Yes</span>
								<button type="submit" name="command" value="requestreceipt">Request
									receipt</button>
								<br>
							</c:if> <c:if
								test="${item.associatedMedicine.isReceiptRequired eq 'false'}">
								<span>No</span><br>
							</c:if> 
							Price per medicine: ${item.associatedMedicine.price}$<br>
							<c:if test="${not empty sessionScope.messageFromReceiptService}">
							<c:if test="${sessionScope.medId == item.associatedMedicine.id}">
								<div style="color: blue;">${sessionScope.messageFromReceiptService}</div>
								${sessionScope.remove('messageFromReceiptService')}
								${sessionScope.remove('medId')}
							</c:if>
						</c:if>
						</label>
					</form>
				</c:forEach>
				<form action="${pageContext.request.contextPath}/mainController" method="post">
						<c:if test="${not empty sessionScope.items}">
							<button type="submit" name="command" value="payforcart">Pay for the shopping cart</button>
							<button type="submit" name="command" value="cartreset">Reset cart</button><br>
						</c:if>
						
						<c:if test="${not empty sessionScope.itemsDeletingResult}">
							${sessionScope.itemsDeletingResult}<br>
							${sessionScope.remove('itemsDeletingResult')}
						</c:if>
						
						<c:if test="${empty sessionScope.items}">
							<span>Shopping cart is empty</span>
						</c:if>
				</form>
				
				<c:if test="${not empty sessionScope.paymentResult}">
					Result: ${sessionScope.paymentResult}<br>
					${sessionScope.remove('paymentResult')}
				</c:if>
				
				<c:if test="${not empty sessionScope.totalPrice }">
					Total Price: ${sessionScope.totalPrice}$<br>
					${sessionScope.remove('totalPrice')}
				</c:if>
				
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>