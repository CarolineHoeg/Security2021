<%--
  Created by IntelliJ IDEA.
  User: carol
  Date: 11-10-2021
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<h1>Welcome!</h1>
<p>Hi ${user.getUsername()}</p>
<!-- Logout -->
<form name="logout" action="ServletController" method="post">
    <input type="hidden" name="cmd" value="logout">
    <input type="submit" value="Logout"/>
</form>
</body>
</html>
