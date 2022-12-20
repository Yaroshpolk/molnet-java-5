package com.yaroshevich.app.dao;

import com.yaroshevich.app.mapper.EmployeeMapper;
import com.yaroshevich.app.model.Employee;
import com.yaroshevich.app.util.DBConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class EmployeeDao implements Dao<Employee> {


    public List<Employee> getAll() throws SQLException {
        Connection connection = DBConnector.getConnection();

        Statement statement = connection.createStatement();
        EmployeeMapper mapper = new EmployeeMapper();

        List<Employee> resultList = mapper.map(statement.executeQuery("SELECT employees.id as employee_id,\n" +
                "       first_name,\n" +
                "       last_name,\n" +
                "       patronymic,\n" +
                "       age,\n" +
                "       ha.id        as address_id,\n" +
                "       ha.address,\n" +
                "       d.id as district_id,\n" +
                "       d.district_name,\n" +
                "       d.parent_id as district_parent_id,\n" +
                "       d2.district_name as district_parent,\n" +
                "       s.id         as shift_id,\n" +
                "       s.start_at,\n" +
                "       s.end_at\n" +
                "FROM employees\n" +
                "         JOIN home_addresses ha on ha.id = employees.address_id\n" +
                "         JOIN districts d on d.id = ha.district_id\n" +
                "         JOIN districts d2 on d2.id = d.parent_id\n" +
                "         JOIN shifts s on s.id = employees.shift_id\n" +
                "ORDER BY last_name, first_name, patronymic"));

        connection.close();

        return resultList;
    }

    public Employee getById(int id) throws SQLException {
        Connection connection = DBConnector.getConnection();
        Statement statement = connection.createStatement();
        EmployeeMapper mapper = new EmployeeMapper();

        List<Employee> resultList = mapper.map(statement.executeQuery("SELECT employees.id as employee_id,\n" +
                "       first_name,\n" +
                "       last_name,\n" +
                "       patronymic,\n" +
                "       age,\n" +
                "       ha.id        as address_id,\n" +
                "       ha.address,\n" +
                "       d.id as district_id,\n" +
                "       d.district_name,\n" +
                "       d.parent_id as district_parent_id,\n" +
                "       d2.district_name as district_parent,\n" +
                "       s.id         as shift_id,\n" +
                "       s.start_at,\n" +
                "       s.end_at\n" +
                "FROM employees\n" +
                "         JOIN home_addresses ha on ha.id = employees.address_id\n" +
                "         JOIN districts d on d.id = ha.district_id\n" +
                "         JOIN districts d2 on d2.id = d.parent_id\n" +
                "         JOIN shifts s on s.id = employees.shift_id\n" +
                "WHERE employees.id = " + id));

        connection.close();
        return resultList.get(0);
    }

    public void add(Employee employee) throws SQLException {
        Connection connection = DBConnector.getConnection();
        Statement statement = connection.createStatement();
        EmployeeMapper mapper = new EmployeeMapper();

        statement.executeQuery("INSERT INTO employees(first_name, last_name, patronymic, age, address_id, shift_id) " +
                "VALUES (" + employee.getFirstName() + "," + employee.getSecondName() + "," + employee.getPatronymic() +
                "," + employee.getAge() + "," + employee.getAddress().getId() + "," + employee.getShift().getId());

        connection.close();
    }

    public Map<Integer, Integer> countAges() throws SQLException {
        Connection connection = DBConnector.getConnection();
        Statement statement = connection.createStatement();
        EmployeeMapper mapper = new EmployeeMapper();

        Map<Integer, Integer> result = mapper.mapAges(statement.executeQuery(
                "SELECT age, count(age)\n" +
                        "FROM employees\n" +
                        "GROUP BY age"));

        connection.close();
        return result;
    }

}
