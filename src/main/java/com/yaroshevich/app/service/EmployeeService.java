package com.yaroshevich.app.service;

import com.yaroshevich.app.dao.EmployeeDao;
import com.yaroshevich.app.model.Employee;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

public class EmployeeService {

    private final EmployeeDao employeeDao;

    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public List<Employee> getAll() throws SQLException {
        List<Employee> result = employeeDao.getAll();

        return result;
    }

    public Employee getById(int id) throws SQLException {
        return employeeDao.getById(id);
    }

}
