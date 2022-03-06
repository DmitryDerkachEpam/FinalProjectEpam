<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Pharmacist's page</title>
    <link rel="stylesheet" href="css/stylesPharmacist.css">
</head>
<body>
 <%@ include file="header.jsp"%>
<div id="wrapper">
    <div class="main">
        <div id="sidebar">
            <ul>
                <li><a href="#">Medicine list</a></li>
            </ul>
        </div>
        <div id="content">
            <div id="addNewMedicine">
                <form action="#" method="post">
                    <label for="medicineName">Name
                        <input id="medicineName" name="medicineName" type="text">
                    </label>
                    <label for="medicineDose">Dose
                        <input id="medicineDose" name="medicineDose" type="text">
                    </label>
                    <label for="userName">Price
                        <input id="userName" name="medicinePrice" type="text">
                    </label>
                    <label>Is receipt required:
                        <input name="isReceiptRequired" type="radio" value="false" checked>No
                        <input name="isReceiptRequired" type="radio" value="true">Yes
                    </label>
                    <input id="submitButton" type="submit" value="Add a new medicine" >
                </form>
            </div>
            <h1>Content. Content. Content. Content. Content. Content. Content. Content.
                Content. Content. Content. Content. Content Content Content. Content.</h1>
            <h1>Content.</h1>
            <h1>Content.</h1>
            <h1>Content.</h1>
            <h1>Content.</h1>
            <h1>Content.</h1>
            <h1>Content.</h1>
            <h1>Content.</h1>
            <h1>Content.</h1>
        </div>
    </div>
</div>
 <%@ include file="footer.jsp"%>
</body>
</html>
