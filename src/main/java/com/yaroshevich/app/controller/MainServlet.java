package com.yaroshevich.app.controller;

import com.yaroshevich.app.dao.DistrictDao;
import com.yaroshevich.app.dao.EmployeeDao;
import com.yaroshevich.app.filter.FilterDataObject;
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

@WebServlet(name = "MainServlet", value = "/app/main")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {

            int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 0;
            request.setAttribute("page", page);

            EmployeeDao employeeDao = new EmployeeDao();

            List<Employee> employees;

            if (request.getSession().getAttribute("filterData") != null) {
                employees =
                        employeeDao.getWithFilter((FilterDataObject) request.getSession().getAttribute("filterData"),
                                page * 10, 10);

                request.getSession().setAttribute("rowsCount",
                        employeeDao.countRows((FilterDataObject)
                                request.getSession().getAttribute("filterData")));

            } else {
                FilterDataObject filterData = new FilterDataObject(0, 0, 1, "");
                employees = employeeDao.getWithFilter(filterData, page * 10, 10);

                request.getSession().setAttribute("filterData", filterData);
                request.getSession().setAttribute("rowsCount", employeeDao.countRows(filterData));
            }

            DistrictDao districtDao = new DistrictDao();
            List<District> districts = districtDao.getDistricts();
            List<District> regions = districtDao.getRegions();

            request.setAttribute("employees", employees);
            request.setAttribute("districts", districts);
            request.setAttribute("regions", regions);

            request.getRequestDispatcher("/view/index.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
