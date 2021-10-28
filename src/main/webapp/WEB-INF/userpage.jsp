<%--
  Created by IntelliJ IDEA.
  User: carol
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Userpage</title>
</head>
<body>
<h1>Welcome!</h1>
<p>Hi <c:out value="${user.getUsername()}"></c:out> </p>

<!-- Create Forum -->
<form name="forum" action="ServletController" method="post">
    <input type="hidden" name="cmd" value="forum">
    <input type="hidden" name="u_name" value="${user.getUsername()}">
    <input type="text" name="f_title" placeholder="Title" required>
    <input type="text" name="f_content" placeholder="Content" required>
    <input type="submit" value="Create new forum">
</form>


<!-- Logout -->
<form name="logout" action="ServletController" method="post">
    <input type="hidden" name="cmd" value="logout">
    <input type="submit" value="Logout"/>
</form>
</body>
</html>