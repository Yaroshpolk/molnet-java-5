package com.yaroshevich.app.controller;

import com.yaroshevich.app.dao.EmployeeDao;
import com.yaroshevich.app.model.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "EmployeeServlet", value = "/app/employee")
public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {

            EmployeeDao employeeDao = new EmployeeDao();

            Employee employee = null;

            try {
                int id = Integer.parseInt(request.getParameter("id"));
                employee = employeeDao.getById(id);
                request.setAttribute("employee", employee);
                request.getRequestDispatcher("/view/profile.jsp").forward(request, response);

            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
