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
	<c:forEach var="medicines" items="${sessionScope.medicines}">

					<form action="${pageContext.request.contextPath}/mainController"
						method="post">

						<label> <input type="hidden" name="medicineId"
							value="${medicines.id}" />
				      <div style = "position:relative; left:400px; top:0px; bottom:0px">
        <img alt="" src="${pageContent.request.contextPath}/images/${medicines.medicineImageKey}" height="100" width="300">
      </div>
      						<div style = "position:relative; left:0px; top:-100px; bottom: 0px">
							${medicines.name} <br> 
							Active dose:
							${medicines.dose} mg<br> 
							Receipt
							required: <c:if
								test="${medicines.isReceiptRequired eq 'true'}">
								<span>Yes</span>
							</c:if> 

							<c:if
								test="${medicines.isReceiptRequired eq 'false'}">
								<span>No</span>
							</c:if> <br>  
							Price: ${medicines.price}$<br>
							
							<input type="number" name="quantity" value="0" min="1" max="10" />
							<button type="submit" name="command" value="addtocart">Add
								to cart</button> 
							<c:if test="${not empty sessionScope.message}">
								<c:if
									test="${sessionScope.messageId == sessionScope.medicines.get(index).id}">
									<div style="color: green;">${sessionScope.medicines.get(index).name}
										${sessionScope.message}</div>
										 ${sessionScope.remove('message')}
								</c:if> 
							</c:if> 
							</div>
						</label>
	
					</form>
				</c:forEach>
				
				
				   <%--For displaying Page numbers. The when condition does not display a link for the current page--%>
				
 				<table border="0" cellpadding="0" cellspacing="5">
			        <tr>		            
 			             <c:forEach begin="1" end="${sessionScope.numOfPages}" var="i">
		                    <form action="${pageContext.request.contextPath}/mainController" method="post">
		                    	<input type="hidden" name="page" value="${i}"/>
		                    	<td> <button type="submit" name="command" value="showallmedicines">${i}</button></td>		 
							</form> 
			            </c:forEach>
			        </tr>
			    </table> 
							
				
				
				
				
				
				
				
				
				
				
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>