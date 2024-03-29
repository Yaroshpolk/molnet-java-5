package com.yaroshevich.app.controller;

import com.yaroshevich.app.filter.FilterDataObject;
import com.yaroshevich.app.util.EmployeeExcelGenerator;
import com.yaroshevich.app.util.PropertiesHelper;
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
        request.setCharacterEncoding("UTF-8");

        EmployeeExcelGenerator excelGenerator = new EmployeeExcelGenerator();
        String fileName = PropertiesHelper.properties.getProperty("excel.fileName");

        int districtId = Integer.parseInt(request.getParameter("filter_district"));
        int regionId = Integer.parseInt(request.getParameter("filter_region"));
        int sortType = Integer.parseInt(request.getParameter("filter_sortType"));
        String search = request.getParameter("search_field");

        FilterDataObject filterData = new FilterDataObject(districtId, regionId, sortType, search);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", String.format("attachment; filename=%s.xlsx", fileName));

        try (ServletOutputStream out = response.getOutputStream()) {
            excelGenerator.generateExcel(out, filterData);
        }

    }
}
