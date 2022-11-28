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

    public List<Employee> getAll() throws SQLException {
        Statement statement = connection.createStatement();
        EmployeeMapper mapper = new EmployeeMapper();

        return mapper.map(statement.executeQuery("SELECT employees.id as employee_id, first_name, last_name," +
                " patronymic, age, ha.id as address_id, ha.address, ha.district, ha.region, s.id as shift_id, " +
                "s.start_at, s.end_at FROM employees\n" +
                "LEFT JOIN home_addresses ha on ha.id = employees.address_id\n" +
                "LEFT JOIN shifts s on s.id = employees.shift_id"));
    }

    public Employee getById(int id) throws SQLException {
        Statement statement = connection.createStatement();
        EmployeeMapper mapper = new EmployeeMapper();

        List<Employee> resultList = mapper.map(statement.executeQuery("SELECT employees.id as employee_id, " +
                "first_name, last_name, patronymic, age, ha.id as address_id, ha.address, ha.district, ha.region, " +
                "s.id as shift_id, s.start_at, s.end_at FROM employees\n" +
                "LEFT JOIN home_addresses ha on ha.id = employees.address_id\n" +
                "LEFT JOIN shifts s on s.id = employees.shift_id WHERE employees.id = " + id));

        return resultList.get(0);
    }

}
