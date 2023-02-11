<%-- 
    Document   : user
    Created on : Dec 23, 2022, 3:31:32 PM
    Author     : SMART
--%>

<%@page import="com.mycompany.main.Context" %>
<%@page import="com.mycompany.dao.inter.UserDaoInter" %>
<%@page import="com.mycompany.entity.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
<%
    User u = (User) request.getAttribute("user");
%>

<div>
    <form action="userdetail" method="POST">
        <%--@declare id="name"--%><%--@declare id="surname"--%>
        <input type="hidden" name="id" value="<%=u.getId()%>"/>
        <label for="name">name:</label>
        <input type="text" name="name" value="<%=u.getName()%>"/>
        <br>
        <label for="surname"> surname: </label>
        <input type="text" name="surname" value="<%=u.getSurname()%>">
        <input type="submit" name="action" value="update">
    </form>
</div>
</body>
</html>
