<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>Add new User</title>
</head>
<body>
    <form method="post" action="UserController" name="AddUser">
        <input type="text" hidden name="id" value="<c:out value="${user.id}"/>"><br>
        First Name: <input type="text" name="firstName" value="<c:out value="${user.firstName}"/>"><br>
        Last Name: <input type="text" name="lastName" value="<c:out value="${user.lastName}"/>"><br>
        DOB: <input type="text" name="dob" value="<fmt:formatDate value="${user.dob}" pattern="MM/dd/yyyy"/>"><br>
        Email: <input type="email" name="email" value="<c:out value="${user.email}"/>"><br>
        <input type="submit" value="Add User"/>
    </form>
</body>
</html>
