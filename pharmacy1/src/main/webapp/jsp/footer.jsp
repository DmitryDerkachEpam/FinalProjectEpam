<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tomcat.apache.org/example-taglib" prefix="m" %>  
<html>
<head>
    <meta charset="utf-8">
    <title>Footer</title>
    <!-- <link rel="stylesheet" href="css/stylesFooter.css"> -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/css/stylesFooter.css" />
</head>
<body>

<div id="wrapper">
    <div id="footer">
     Current Date and Time is: <m:today/>
     <div><a href="https://github.com/DmitryDerkachEpam?tab=repositories"> Dmitry Derkach Pharmacy (c) 2022</a></div> 
    
    </div>
</div>
</body>
</html>
