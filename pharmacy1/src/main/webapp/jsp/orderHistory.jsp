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
				<c:forEach var="item" items="${sessionScope.userOrders}">
					<form action="${pageContext.request.contextPath}/mainController" method="post">
						<input type="hidden" name="orderId" value="${item.id}" />
						Order ID: ${item.id}
						<button type="submit" name="command" value="showorder">Show additional detail</button>
						<br>
						Bought date: ${item.date}<br>
						<br>
					</form>
				</c:forEach>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>