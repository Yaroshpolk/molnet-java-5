package com.yaroshevich.app.service;

import com.yaroshevich.app.dao.EmployeeDao;
import com.yaroshevich.app.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService {

    private final EmployeeDao employeeDao;

    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public List<EmployeeDto> getAllWithDetails() throws SQLException {
        return employeeDao.getAllWithDetails();
    }

}
