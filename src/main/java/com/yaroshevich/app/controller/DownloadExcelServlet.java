package com.yaroshevich.app.controller;

import com.yaroshevich.app.util.EmployeeExcelGenerator;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DownloadExcelServlet", value = "/app/downloadExcel")
public class DownloadExcelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EmployeeExcelGenerator excelGenerator = new EmployeeExcelGenerator();

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=employees.xlsx");

        try (ServletOutputStream out = response.getOutputStream()) {
            excelGenerator.generateExcel(out);
        }

    }
}
