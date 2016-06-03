<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script type="text/javascript" src="myscript.js"></script>
    <title>Edit Page</title>
</head>
<body>
<jsp:useBean id="editingUser" class="ua.hypson.hibernatelab.entity.User" scope="session"/>
<h2>Edit user</h2>
<form action="EditorServ" method="post" onsubmit="return myFunction() && validateEmail()">

    <table>
        <tr>
            <td>Login</td>
            <td><input type="text" name="login" value="${editingUser.login}" disabled/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="text" name="password" value="${editingUser.password}" id="password"/></td>
        </tr>
        <tr>
            <td>Repeat password</td>
            <td><input type="text" name="password2" value="${editingUser.password}" id="password2"/></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name="email" value="${editingUser.email}" id="email"/></td>
        </tr>
        <tr>
            <td>First Name</td>
            <td><input type="text" name="firstName" value="${editingUser.firstName}"/></td>
        </tr>
        <tr>
            <td>Last Name</td>
            <td><input type="text" name="lastName" value="${editingUser.lastName}"/></td>
        </tr>
        <tr>
            <td>Date of birth</td>
            <td><input type="date" name="birthday" value="${editingUser.birthday}"/></td>
        </tr>
        <tr>
            <td>Role</td>
            <td>Regular<input type="radio" name="role"
                              value="regular" ${editingUser.role.name == 'regular' ? 'checked' : ''} />
                Admin<input type="radio" name="role"
                            value="admin" ${editingUser.role.name == 'admin' ? 'checked' : ''} /></td>
        </tr>
    </table>
    <br>
    <input type="submit" value="Edit">
    <form action="LoginServ" method="post">
        <button name="cancel" value="cancel">Cancel</button>
    </form>
</form>
</body>
</html>