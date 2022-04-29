<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Header</title>
  	<!--   <link rel="stylesheet" href="css/stylesHeader.css"> -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/css/stylesHeader.css" />
</head>
<body>
<div id="wrapper">
    <div id="header">
        <div id="toMainPage"><a href="#">Pharmacy</a></div>
        <div id="twoButtons">
            <div id="language">
                <div class="dropdown">
                    <button class="dropBtn">Lang</button>
                    <div class="dropdown-content">
                        <a href="#">English</a>
                        <a href="#">Русский</a>
                        <a href="#">Беларуская</a>
                    </div>
                </div>
            </div>
            <div id="exit">
                <div class="dropdown">
						<form action="${pageContext.request.contextPath}/mainController" method="post">
                        	<button class="dropBtn" type="submit" name="command" value="logout">Logout</button>
                  		</form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>		