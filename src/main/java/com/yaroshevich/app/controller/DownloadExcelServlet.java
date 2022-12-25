package com.yaroshevich.app.controller;

import com.yaroshevich.app.dataObject.FilterDataObject;
import com.yaroshevich.app.model.Employee;
import com.yaroshevich.app.util.EmployeeExcelGenerator;
import com.yaroshevich.app.util.PropertiesHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DownloadExcelServlet", value = "/app/downloadExcel")
public class DownloadExcelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EmployeeExcelGenerator excelGenerator = new EmployeeExcelGenerator();
        String fileName = PropertiesHelper.properties.getProperty("excel.fileName");

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", String.format("attachment; filename=%s.xlsx", fileName));

        try (ServletOutputStream out = response.getOutputStream()) {
            excelGenerator.generateExcel(out, (FilterDataObject) request.getSession().getAttribute("filterData"));
        }

    }
}
