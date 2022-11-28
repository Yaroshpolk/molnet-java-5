package com.yaroshevich.app.service;

import com.yaroshevich.app.dao.EmployeeDao;
import com.yaroshevich.app.model.Employee;

import java.util.Comparator;
import java.util.List;

public class EmployeeService {

    private final EmployeeDao employeeDao;

    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public List<Employee> getAll() {
        List<Employee> result = employeeDao.getAll();

        result.sort(Comparator.comparing(Employee::getSecondName).thenComparing(Employee::getFirstName)
                .thenComparing(Employee::getPatronymic));

        return result;
    }

    public Employee getById(int id) {
        return employeeDao.getById(id);
    }

}
