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
								value="showusercart">Receipts status</button>
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
				</ul>
			</div>
			<div id="content">
				<form action="${pageContext.request.contextPath}/mainController" method="post">
					<%-- <c:forEach var="medicine" items="${sessionScope.medicines}"> --%>			 
						<!-- <h3> -->
						<!-- 	<label>  -->
<%-- 							<input type="hidden" name="medicineId" value="${medicine.id}" /> ${medicine.name} <br> 
							Active dose: ${medicine.dose} mg<br> 
							Receipt required: 
							<c:if
								test="${medicine.isReceiptRequired eq 'true'}">
								<span>Yes</span>
							</c:if> 
							<c:if test="${medicine.isReceiptRequired eq 'false'}">
								<span>No</span>
							</c:if> <br> 
							Price: ${medicine.price}$<br> 
							
							<input type="number" name="quantity" value="0" />
							<button type="submit" name="command" value="addtocart">Add to cart</button> <br>	 --%>
						<!-- 		</label> -->
						<!-- </h3> -->
				<%-- 	</c:forEach> --%>
				
				
				
				
						 <c:forEach var="index" begin="0" end=${sessionScope.medicines.size()}> 
						 	<input type="hidden" name="medicineId" value="${sessionScope.medicines.id}" /> 
						 	${medicine.name} <br> 
							Active dose: ${medicine.dose} mg<br> 
							Receipt required: 
							<c:if
								test="${medicine.isReceiptRequired eq 'true'}">
								<span>Yes</span>
							</c:if> 
							<c:if test="${medicine.isReceiptRequired eq 'false'}">
								<span>No</span>
							</c:if> <br> 
							Price: ${medicine.price}$<br> 
							
							<input type="number" name="quantity" value="0" />
							<button type="submit" name="command" value="addtocart">Add to cart</button> <br>	
						 </c:forEach>
					
		
				</form>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>