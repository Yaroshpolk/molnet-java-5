package com.yaroshevich.app.dao;

import com.yaroshevich.app.mapper.EmployeeMapper;
import com.yaroshevich.app.model.Employee;
import com.yaroshevich.app.util.DBWorker;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDao implements Dao<Employee> {

    private final Connection connection;

    public EmployeeDao(Connection connection) {
        this.connection = connection;
    }

    public List<Employee> getAll() throws SQLException {
        DBWorker dbWorker = new DBWorker(connection);
        EmployeeMapper mapper = new EmployeeMapper();

        return mapper.map(dbWorker.executeQuery("SELECT employees.id as employee_id, first_name, last_name," +
                " patronymic, age, ha.id as address_id, ha.address, ha.district, ha.region, s.id as shift_id, " +
                "s.start_at, s.end_at FROM employees\n" +
                "LEFT JOIN home_addresses ha on ha.id = employees.address_id\n" +
                "LEFT JOIN shifts s on s.id = employees.shift_id"));
    }

}
