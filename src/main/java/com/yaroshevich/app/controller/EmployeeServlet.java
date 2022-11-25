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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EmployeeServlet", value = "/employee")
public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = new DBConnector().getConnection();

        EmployeeDao employeeDao = new EmployeeDao(connection);

        EmployeeService employeeService = new EmployeeService(employeeDao);

        List<Employee> employees = new ArrayList<>();

        try {
            employees = employeeService.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("employees", employees);

        request.getRequestDispatcher("/view/index.jsp").forward(request, response);

    }
}
