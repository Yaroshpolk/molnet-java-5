package com.yaroshevich.app.controller;

import com.yaroshevich.app.filter.FilterDataObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "FilterServlet", value = "/app/main/filter")
public class FilterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int districtId = Integer.parseInt(request.getParameter("filter_district"));
        int regionId = Integer.parseInt(request.getParameter("filter_region"));
        int sortType = Integer.parseInt(request.getParameter("filter_sortType"));
        String search = request.getParameter("search_field");

        FilterDataObject filterData = new FilterDataObject(districtId, regionId, sortType, search);
        request.getSession().setAttribute("filterData", filterData);

        request.getRequestDispatcher("/app/main").forward(request, response);


    }

}
