<%@ page import="com.mycompany.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: SMART
  Date: 2/25/2023
  Time: 12:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%
    User user = (User) session.getAttribute("loggedInUser");
    if(2==2)
    throw new IllegalArgumentException("hey");
%>
<%--<%="Welcome, "+user.getName() + "!!!"%>--%>
