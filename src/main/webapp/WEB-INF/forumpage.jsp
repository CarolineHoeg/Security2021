<%--
  Created by IntelliJ IDEA.
  User: carol
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Forum</title>
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.2/css/bootstrap.min.css'>
    <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.3.1/css/all.css'>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<%@include file="includes/navbar.jsp" %>
<div class="container-fluid">
<h1>Forum</h1>
    <%@include file="includes/createforum.jsp" %>

    <div class="container">
<ul class="list-unstyled">
    <c:forEach items="${forums}" var="forum">
    <li class="media">
        <div class="card card-body">
            <h3 class="mt-0"><c:out value="${forum.getTitle()}"/></h3>
            <h6>By <c:out value="${forum.getUsername()}"/></h6>
            <p><c:out value="${forum.getContent()}"/></p>
            <p>${forum.getComments().size()} comments</p>
            <c:if test="${user != null}">
                <button>Add comment</button>
            </c:if>
        </div>
    </li>
    </c:forEach>
</ul>
    </div>
</div>
<script src="js/script.js"></script>
</body>
</html>
