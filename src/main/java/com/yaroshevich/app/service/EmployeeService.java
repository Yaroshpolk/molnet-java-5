package com.yaroshevich.app.service;

import com.yaroshevich.app.dao.EmployeeDao;
import com.yaroshevich.app.model.Employee;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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

    public Map<Integer, Integer> countAges() throws SQLException {
        return employeeDao.countAges();
    }

}
