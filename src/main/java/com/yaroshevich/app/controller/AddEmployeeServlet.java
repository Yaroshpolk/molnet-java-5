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
        int employeeAge = Integer.parseInt(request.getParameter("employee_age"));
        String employeeAddress = request.getParameter("employee_address");
        int employeeParentDistrictId = Integer.parseInt(request.getParameter("employee_district"));
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
                if (shiftDao.getByTime(new Shift(employeeStart, employeeEnd)) != null) {
                    shift = shiftDao.getByTime(new Shift(employeeStart, employeeEnd));
                } else {
                    shift = shiftDao.add(new Shift(employeeStart, employeeEnd));
                }
            }

            if (addressDao.getByParams(new Address(employeeAddress, district)) != null) {
                address = addressDao.getByParams(new Address(employeeAddress, district));
            } else {
                address = addressDao.add(new Address(employeeAddress, district));
            }

            Employee employee = employeeDao.add(new Employee(employeeFirstName, employeeSecondName, employeePatronymic,
                    employeeAge, address, shift));

            response.sendRedirect("/app/employee?id="+employee.getId());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
