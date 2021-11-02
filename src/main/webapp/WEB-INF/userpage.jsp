<%--
  Created by IntelliJ IDEA.
  User: carol
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Userpage</title>
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.2/css/bootstrap.min.css'>
    <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.3.1/css/all.css'>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<%@include file="includes/navbar.jsp" %>
<div class="container-fluid">
<h1>Welcome!</h1>
<p>Hi <c:out value="${user.getUsername()}"/></p>
<!-- Logout -->
<form name="logout" action="ServletController" method="post">
    <input type="hidden" name="cmd" value="logout">
    <input type="submit" value="Logout"/>
</form>
</div>
</body>
</html>