<%@ page import="com.company.entity.User" %>
<%@ page import="com.mycompany.Main.Context" %>
<%@ page import="com.mycompany.dao.inter.UserDaoInter" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%><%--
    Document   : user
    Created on : Dec 23, 2022, 3:31:32 PM
    Author     : SMART
--%>



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%--        <link rel="stylesheet" href="https://kit.fontawesome.com/844b95d7f5.css" crossorigin="anonymous">--%>
        <link rel="stylesheet" href="assets/css/users.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>JSP Page</title>

    </head>

<body>
<% UserDaoInter userDao = Context.instanceUserDao();

    String name = request.getParameter("name");
    String surname = request.getParameter("surname");

    String nationalityStr = request.getParameter("nid");
    Integer nationalityId = null;
    if (nationalityStr != null && !nationalityStr.trim().isEmpty()) {
        nationalityId = Integer.parseInt(nationalityStr);
    }


    List<User> list = userDao.getAll(name, surname, nationalityId);
%>
       <div class="container mycontainer">
             <div class="row">
                     <div class="col-4">
                     <form action="users.jsp" method="POST"><input type="hidden" name="id" value=""/>
                        <div class="form-group">
                            <label for="name">name:</label>
                            <input placeholder="Enter name" class="form-control" id="name" type="text" name="name" value=""/>
                        </div>
                        <div class="form-group">
                            <label for="surname">surname:</label>
                            <input placeholder="Enter surname" class="form-control" id="surname" type="text" name="surname" value=""/>
                        </div>

                            <input class="btn btn-primary" type="submit" name="search" value="Search"/>
                    </form>
                     </div>

             </div>
                <div>
               <table class="table">
                   <thead>
                   <tr>
                       <th>name</th>
                       <th>surname</th>
                       <th>nationality</th>
                       <th></th>
                   </tr>
                   </thead>
                   <tbody>
                   <% for (User u : list) { %>
                   <tr>
                       <td><%=u.getName()%></td>
                       <td><%=u.getSurname()%></td>
                       <td><%=u.getNationality().getName() == null ? "N/A" : u.getNationality().getName()%></td>
                       <td>
                           <button class="btn btn-danger" type="submit" value="delete" name="action">
                               <i class="fa-solid fa-trash-can"></i>
                           </button>

                           <button class="btn btn-secondary" type="submit" value="update" name="action">
                               <i class="fa-sharp fa-solid fa-square-pen"></i>
                           </button>
                       </td>
                   </tr>
                   <%}%>

                   </tbody>
               </table>
           </div>
       </div>


<script src="https://kit.fontawesome.com/844b95d7f5.js" crossorigin="anonymous"></script>

</body>
</html>