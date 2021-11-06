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
            <c:if test="${forum.getImageUrl() != null}">
                <img src="${forum.getImageUrl()}" alt="Image" style="max-width:400px; max-height:400px;">
            </c:if>
            <h6>By <c:out value="${forum.getUsername()}"/></h6>
            <p><c:out value="${forum.getContent()}"/></p>
            <p>${forum.getComments().size()} comments</p>
            <c:forEach items="${forum.getComments()}" var="comment">
            <div class="card card-body">
                <h6>By <c:out value="${comment.getUsername()}"/></h6>
                <p><c:out value="${comment.getContent()}"/></p>
            </div>
            </c:forEach>
            <c:if test="${user != null}">
                <form name="create_comment" action="ServletController" method="post">
                    <input type="hidden" name="cmd" value="comment">
                    <input type="hidden" name="commentcmd" value="create">
                    <input type="hidden" name="u_name" value="${forum.getUsername()}">
                    <input type="hidden" name="f_id" value="${forum.getId()}">
                    <input type="text" name="content" required>
                    <input type="submit" value="Add comment">
                </form>
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
