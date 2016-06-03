<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Success</title>
</head>
<body>
<jsp:useBean id="user" class="ua.hypson.hibernatelab.entity.User" scope="session"/>

<h2 align="center">Hello,
    <jsp:getProperty property="firstName" name="user"/>
    !</h2><br>
<p align="center">Click <a href="LogOutServ">here</a> to log out</p>

</body>
</html>