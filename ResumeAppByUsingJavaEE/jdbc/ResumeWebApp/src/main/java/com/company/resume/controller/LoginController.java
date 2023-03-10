/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.company.resume.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.resume.util.ControllerUtil;
import com.mycompany.dao.inter.UserDaoInter;
import com.mycompany.entity.User;
import com.mycompany.main.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author SMART
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    private final UserDaoInter userDao = Context.instanceUserDao();

    private static BCrypt.Verifyer verifyer = BCrypt.verifyer();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = request.getParameter("email");

            System.out.println("email="+email);
            String password = request.getParameter("password");

            System.out.println("password="+password);
            User user = userDao.findByEmail(email);

            if (user == null) {
                throw new IllegalArgumentException("User doesn't exist");
            }

            System.out.println("hash="+user.getPassword());
            BCrypt.Result rs =verifyer.verify(password.toCharArray(), user.getPassword().toCharArray());
            if(!rs.verified){
                    throw new IllegalArgumentException("password is incorrect!!!");
            }

            request.getSession().setAttribute("loggedInUser", user);
            response.sendRedirect("users");
        }catch (Exception ex){
            ControllerUtil.errorPage(response, ex);
        }

    }
}
