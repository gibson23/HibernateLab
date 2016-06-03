<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login Page</title>
</head>
<body>
<h4 align="center">Hi, someone! Please log in!</h4>
<form action="LoginServ" method="post">
    <table align="center">
        <tr>
            <td>login:</td>
            <td><input type="text" name="login"></td>
        </tr>
        <tr>
            <td>password:</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td><input type="submit" value="Sign in"></td>
        </tr>
    </table>


</form>

</body>
</html>