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

@WebServlet(name = "RegistrationServlet", value = "/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        request.getRequestDispatcher("/view/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        if (request.getAttribute("error") != null) {
            request.removeAttribute("error");
        }

        if (request.getSession().getAttribute("user") == null) {
            String name = request.getParameter("user_name");
            String login = request.getParameter("user_login");
            String password = null;

            try {
                password = PasswordUtil.encrypt(request.getParameter("user_password"), PasswordUtil.getSalt());
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

            if (name != null && login != null && password != null) {
                User user = new User(name, login, password);

                try {
                    UserDao userDao = new UserDao();

                    if (userDao.isLoginAvailable(login)) {
                        User newUser = userDao.add(user);

                        if (newUser != null) {
                            request.getSession().setAttribute("user", newUser);
                            response.sendRedirect("/app/main");
                        } else {
                            response.sendRedirect("/registration");
                        }
                    } else {
                        request.setAttribute("error", "user already exists");
                        doGet(request, response);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else {
                response.sendRedirect("/registration");
            }
        } else {
            response.sendRedirect("/app/main");
        }
    }
}
