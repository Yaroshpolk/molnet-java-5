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

@WebServlet(name = "EmployeeServlet", value = "/app/employee")
public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = new DBConnector().getConnection();

        EmployeeDao employeeDao = new EmployeeDao(connection);

        EmployeeService employeeService = new EmployeeService(employeeDao);

        Employee employee = null;

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            employee = employeeService.getById(id);
            request.setAttribute("employee", employee);
            request.getRequestDispatcher("/view/profile.jsp").forward(request, response);

        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

    }
}
