package com.yaroshevich.app.controller;

import com.yaroshevich.app.dao.UserDao;
import com.yaroshevich.app.model.User;
import com.yaroshevich.app.util.PasswordUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name = "AuthServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        if (request.getSession().getAttribute("user") == null) {
            request.getRequestDispatcher("/view/login.jsp").forward(request, response);
        } else {
            response.sendRedirect("/app/main");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        if (request.getAttribute("error") != null) {
            request.removeAttribute("error");
        }

        if (request.getSession().getAttribute("user") == null) {
            String login = request.getParameter("user_login");
            String password = request.getParameter("user_password");

            try {
                UserDao userDao = new UserDao();
                User user = userDao.getByLogin(login);

                if (user != null) {

                    String salt = user.getPassword().split(":")[0];

                    if (PasswordUtil.encrypt(password, salt).equals(user.getPassword())) {
                        request.getSession().setAttribute("user", user);
                        response.sendRedirect("/app/main");
                    } else {
                        request.setAttribute("error", "user dont exist");
                        doGet(request, response);
                    }

                } else {
                    request.setAttribute("error", "user dont exist");
                    doGet(request, response);
                }

            } catch (SQLException | NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

        } else {
            response.sendRedirect("/app/main");
        }

    }
}
