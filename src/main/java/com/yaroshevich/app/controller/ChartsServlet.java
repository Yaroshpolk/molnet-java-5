package com.yaroshevich.app.controller;

import com.yaroshevich.app.dao.EmployeeDao;
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
import java.util.Map;

@WebServlet(name = "ChartsServlet", value = "/app/charts")
public class ChartsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try (Connection connection = new DBConnector().getConnection();) {

            EmployeeDao employeeDao = new EmployeeDao(connection);

            EmployeeService employeeService = new EmployeeService(employeeDao);

            Map<Integer, Integer> ages = employeeService.countAges();

            request.setAttribute("ages", ages);

            request.getRequestDispatcher("/view/charts.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
