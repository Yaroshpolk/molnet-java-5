package com.yaroshevich.app.controller;

import com.yaroshevich.app.dao.EmployeeDao;
import com.yaroshevich.app.model.Employee;
import com.yaroshevich.app.service.EmployeeService;
import com.yaroshevich.app.util.DBConnector;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "EmployeeServlet", value = "/employee")
public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = new DBConnector().getConnection();

        EmployeeDao employeeDao = new EmployeeDao(connection);

        EmployeeService employeeService = new EmployeeService(employeeDao);

        Employee employee = null;

        int id = Integer.parseInt(request.getParameter("id"));

        try {
            employee = employeeService.getById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("employee", employee);

        request.getRequestDispatcher("/view/profile.jsp").forward(request, response);

    }
}
