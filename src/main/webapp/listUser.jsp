
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" charset="UTF-8" content="text/html">
    <title>List Users</title>
</head>
<body>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>DOB</th>
            <th>Email</th>
            <th colspan="2">Action</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.firstName}"/></td>
                <td><c:out value="${user.lastName}"/></td>
                <td><fmt:formatDate value="${user.dob}" pattern="yyyy-MM-dd"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><a href="UserController?action=edit&id=<c:out value="${user.id}"/>">Edit</a></td>
                <td><a href="UserController?action=delete&id=<c:out value="${user.id}"/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
    <p><a href="UserController?action=insert">Add User</a></p>
</body>
</html>
