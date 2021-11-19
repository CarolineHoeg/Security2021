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

    <!-- While not authenticated -->
    <c:if test="${!user.isValidated()}">
        <form name="validate" action="ServletController" method="post">
            <input type="hidden" name="cmd" value="user">
            <input type="hidden" name="usercmd" value="validate">
            <p>Please check your email and verify your account by entering your one time code:</p>
            <input type="password" name="code" required>
            <input type="submit" value="Authenticate account">
        </form>
        <form name="resendValidation" action="ServletController" method="post">
            <input type="hidden" name="cmd" value="user">
            <input type="hidden" name="usercmd" value="resendValidation">
            <p>If the code doesn't work, or you haven't received any (please check spam folder),
                press to resend email.</p>
            <input type="submit" value="Resend email">
        </form>
    </c:if>
<!-- Logout -->
<form name="logout" action="ServletController" method="post">
    <input type="hidden" name="cmd" value="user">
    <input type="hidden" name="usercmd" value="logout">
    <input type="submit" value="Logout"/>
</form>
</div>
</body>
</html>