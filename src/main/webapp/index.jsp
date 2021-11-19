<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Dev Debate</title>
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.2/css/bootstrap.min.css'>
        <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.3.1/css/all.css'>
    </head>
    <body>
    <%@include file="WEB-INF/includes/navbar.jsp" %>
    <div class="container-fluid">
    <h1>Welcome to Dev Debate</h1>
        <h5>Your place to debate anything computer science!</h5>
        <p>Please log in or sign up and join our great community here</p>
        <c:choose>
            <c:when test="${errorMsg == 'Username or password incorrect. Please try again.'}">
                <script>
                   $(document).ready(function() {
                      $('#loginModal').modal('show');
                  });
             </script>
            </c:when>
            <c:otherwise>
                <p style="color:red"><c:out value="${errorMsg}"/></p>
            </c:otherwise>
        </c:choose>
    </div>
    </body>
</html>