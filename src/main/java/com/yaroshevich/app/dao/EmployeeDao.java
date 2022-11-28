package com.yaroshevich.app.dao;

import com.yaroshevich.app.mapper.EmployeeMapper;
import com.yaroshevich.app.model.Employee;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class EmployeeDao implements Dao<Employee> {

    private final Connection connection;

    public EmployeeDao(Connection connection) {
        this.connection = connection;
    }

    public List<Employee> getAll() {
        Statement statement = null;
        List<Employee> resultList = null;
        EmployeeMapper mapper = new EmployeeMapper();

        try {
            statement = connection.createStatement();
            resultList = mapper.map(statement.executeQuery("SELECT employees.id as employee_id, " +
                    "first_name, last_name, patronymic, age, ha.id as address_id, ha.address, ha.district, ha.region, " +
                    "s.id as shift_id, s.start_at, s.end_at FROM employees\n" +
                    "LEFT JOIN home_addresses ha on ha.id = employees.address_id\n" +
                    "LEFT JOIN shifts s on s.id = employees.shift_id"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultList;
    }

    public Employee getById(int id) {
        Statement statement = null;
        List<Employee> resultList = null;
        EmployeeMapper mapper = new EmployeeMapper();

        try {
            statement = connection.createStatement();
            resultList = mapper.map(statement.executeQuery("SELECT employees.id as employee_id, " +
                    "first_name, last_name, patronymic, age, ha.id as address_id, ha.address, ha.district, ha.region, " +
                    "s.id as shift_id, s.start_at, s.end_at FROM employees\n" +
                    "LEFT JOIN home_addresses ha on ha.id = employees.address_id\n" +
                    "LEFT JOIN shifts s on s.id = employees.shift_id WHERE employees.id = " + id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultList.get(0);
    }

}
