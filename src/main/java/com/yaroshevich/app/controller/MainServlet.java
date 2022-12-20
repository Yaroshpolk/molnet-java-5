package com.yaroshevich.app.controller;

import com.yaroshevich.app.dao.DistrictDao;
import com.yaroshevich.app.dao.EmployeeDao;
import com.yaroshevich.app.model.District;
import com.yaroshevich.app.model.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "MainServlet", value = "/app")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            EmployeeDao employeeDao = new EmployeeDao();
            List<Employee> employees = employeeDao.getAll();

            DistrictDao districtDao = new DistrictDao();
            List<District> districts = districtDao.getDistricts();

            request.setAttribute("employees", employees);
            request.setAttribute("districts", districts);

            System.out.println(districts);

            request.getRequestDispatcher("/view/index.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
