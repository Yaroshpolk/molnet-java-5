package com.yaroshevich.app.dao;

import com.yaroshevich.app.mapper.EmployeeMapper;
import com.yaroshevich.app.model.Employee;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class EmployeeDao implements Dao<Employee> {

    private final Connection connection;

    public EmployeeDao(Connection connection) {
        this.connection = connection;
    }

    public List<Employee> getAll() throws SQLException {
        Statement statement = connection.createStatement();
        EmployeeMapper mapper = new EmployeeMapper();

        List<Employee> resultList = mapper.map(statement.executeQuery("SELECT employees.id as employee_id, " +
                "first_name, last_name, patronymic, age, ha.id as address_id, ha.address, ha.district, ha.region, " +
                "s.id as shift_id, s.start_at, s.end_at FROM employees\n" +
                "JOIN home_addresses ha on ha.id = employees.address_id\n" +
                "JOIN shifts s on s.id = employees.shift_id\n" +
                "ORDER BY last_name, first_name, patronymic"));

        return resultList;
    }

    public Employee getById(int id) throws SQLException {
        Statement statement = connection.createStatement();
        EmployeeMapper mapper = new EmployeeMapper();

        List<Employee> resultList = mapper.map(statement.executeQuery("SELECT employees.id as employee_id, " +
                "first_name, last_name, patronymic, age, ha.id as address_id, ha.address, ha.district, ha.region, " +
                "s.id as shift_id, s.start_at, s.end_at FROM employees\n" +
                "JOIN home_addresses ha on ha.id = employees.address_id\n" +
                "JOIN shifts s on s.id = employees.shift_id WHERE employees.id = " + id));


        return resultList.get(0);
    }

    public Map<Integer, Integer> countAges() throws SQLException {
        Statement statement = connection.createStatement();
        EmployeeMapper mapper = new EmployeeMapper();

        Map<Integer, Integer> result = mapper.mapAges(statement.executeQuery(
                "SELECT age, count(age)\n" +
                        "FROM employees\n" +
                        "GROUP BY age"));

        return result;
    }

}
