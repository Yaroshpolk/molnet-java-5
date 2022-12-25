package com.yaroshevich.app.controller;

import com.yaroshevich.app.dao.AddressDao;
import com.yaroshevich.app.dao.DistrictDao;
import com.yaroshevich.app.dao.EmployeeDao;
import com.yaroshevich.app.dao.ShiftDao;
import com.yaroshevich.app.model.Address;
import com.yaroshevich.app.model.District;
import com.yaroshevich.app.model.Employee;
import com.yaroshevich.app.model.Shift;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddEmployeeServlet", value = "/app/employee/add")
public class AddEmployeeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String employeeFirstName = request.getParameter("employee_firstName");
        String employeeSecondName = request.getParameter("employee_lastName");
        String employeePatronymic = request.getParameter("employee_patronymic");
        Integer employeeAge = request.getParameter("employee_age").equals("") ?
                null : Integer.valueOf(request.getParameter("employee_age"));
        String employeeAddress = request.getParameter("employee_address");
        int employeeDistrictId = Integer.parseInt(request.getParameter("employee_region"));
        String employeeStart = request.getParameter("employee_start");
        String employeeEnd = request.getParameter("employee_end");

        DistrictDao districtDao = new DistrictDao();
        ShiftDao shiftDao = new ShiftDao();
        EmployeeDao employeeDao = new EmployeeDao();
        AddressDao addressDao = new AddressDao();

        Shift shift = null;
        District district = null;
        Address address = null;

        try {

            district = districtDao.getById(employeeDistrictId);


            if (!employeeStart.equals("") && !employeeEnd.equals("")) {
                Shift shiftObj = new Shift(employeeStart, employeeEnd);
                if (shiftDao.getByTime(shiftObj) != null) {
                    shift = shiftDao.getByTime(shiftObj);
                } else {
                    shift = shiftDao.add(shiftObj);
                }

            }

            Address addressObj = new Address(employeeAddress, district);
            if (addressDao.getByParams(addressObj) != null) {
                address = addressDao.getByParams(addressObj);
            } else {
                address = addressDao.add(addressObj);
            }

            Employee employee = employeeDao.add(new Employee(employeeFirstName, employeeSecondName, employeePatronymic,
                    employeeAge, address, shift));

            response.sendRedirect("/app/employee?id=" + employee.getId());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
