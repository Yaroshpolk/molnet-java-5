package com.yaroshevich.app.dao;

import com.yaroshevich.app.filter.FilterDataObject;
import com.yaroshevich.app.mapper.EmployeeMapper;
import com.yaroshevich.app.model.Employee;
import com.yaroshevich.app.util.DBConnector;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class EmployeeDao {

    private static final String EMPLOYEE_SQL = " SELECT employees.id as employee_id,\n" +
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
            "         LEFT JOIN shifts s on s.id = employees.shift_id\n";

    private static final String ADD_EMPLOYEE_SQL = " INSERT INTO employees(first_name, last_name, patronymic, age, " +
            "address_id, shift_id) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String COUNT_AGES_SQL = " SELECT age, COUNT(age) FROM employees GROUP BY age";

    private static final String LIMIT_OFFSET_SQL = " LIMIT ? OFFSET ?";

    private static final String COUNT_EMPLOYEES_SQL = "SELECT count(*) as employee_count\n" +
            "FROM employees\n" +
            "         JOIN home_addresses ha on ha.id = employees.address_id\n" +
            "         JOIN districts d on d.id = ha.district_id\n" +
            "         JOIN districts d2 on d2.id = d.parent_id\n" +
            "         LEFT JOIN shifts s on s.id = employees.shift_id\n";

    public List<Employee> getWithFilter(FilterDataObject filter, int offset, int limit) throws SQLException {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(EMPLOYEE_SQL + filter.generateQuery(true)
                + LIMIT_OFFSET_SQL);

        statement.setInt(1, limit);
        statement.setInt(2, offset);

        EmployeeMapper mapper = new EmployeeMapper();
        List<Employee> resultList = mapper.map(statement.executeQuery());

        connection.close();
        return resultList;
    }

    public Employee getById(int id) throws SQLException {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(EMPLOYEE_SQL + "WHERE employees.id = ?");
        statement.setInt(1, id);

        EmployeeMapper mapper = new EmployeeMapper();
        List<Employee> resultList = mapper.map(statement.executeQuery());

        connection.close();
        return resultList.size() > 0 ? resultList.get(0) : null;
    }

    public Employee add(Employee employee) throws SQLException {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(ADD_EMPLOYEE_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, employee.getFirstName());
        statement.setString(2, employee.getSecondName());
        statement.setString(3, employee.getPatronymic());
        statement.setObject(4, (employee.getAge() == null ? null : employee.getAge()));
        statement.setInt(5, employee.getAddress().getId());
        statement.setObject(6, (employee.getShift() == null ? null : employee.getShift().getId()));

        int res = statement.executeUpdate();

        int id = 1;

        try (ResultSet rows = statement.getGeneratedKeys()) {
            while (rows.next()) {
                id = rows.getInt(1);
            }
        }

        connection.close();
        return getById(id);
    }

    public Map<Integer, Integer> countAges() throws SQLException {
        Connection connection = DBConnector.getConnection();
        Statement statement = connection.createStatement();

        EmployeeMapper mapper = new EmployeeMapper();
        Map<Integer, Integer> result = mapper.mapAges(statement.executeQuery(COUNT_AGES_SQL));

        connection.close();
        return result;
    }

    public int countRows(FilterDataObject filter) throws SQLException {
        Connection connection = DBConnector.getConnection();
        Statement statement = connection.createStatement();

        EmployeeMapper mapper = new EmployeeMapper();
        int result = mapper.mapCount(statement.executeQuery(COUNT_EMPLOYEES_SQL +
                filter.generateQuery(false)));

        connection.close();
        return result;
    }

}
