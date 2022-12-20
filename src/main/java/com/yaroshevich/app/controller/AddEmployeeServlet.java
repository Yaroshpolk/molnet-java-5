package com.yaroshevich.app.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AddEmployeeServlet", value = "/app/employee/add")
public class AddEmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeFirstName = request.getParameter("employee_firstName");
        String employeeSecondName = request.getParameter("employee_lastName");
        String employeePatronymic = request.getParameter("employee_patronymic");
        int employeeAge = Integer.parseInt(request.getParameter("employee_age"));
        String employeeAddress = request.getParameter("employee_address");
        int employeeDistrictId = Integer.parseInt(request.getParameter("employee_district"));
        int employeeParentDistrictId = Integer.parseInt(request.getParameter("employee_region"));
        String employeeStart = request.getParameter("employee_start");
        String employeeEnd = request.getParameter("employee_end");


    }
}
