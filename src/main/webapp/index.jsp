<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Frontpage</title>
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.2/css/bootstrap.min.css'>
        <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.3.1/css/all.css'>
        <link rel="stylesheet" href="WEB-INF/css/styles.css">
    </head>
    <body>
    <%@include file="WEB-INF/includes/navbar.jsp" %>
    <div class="container-fluid">
    <h1>Frontpage</h1>
    <p style="color:red"><c:out value="${errorMsg}"/></p>
    </div>
    </body>
</html>
