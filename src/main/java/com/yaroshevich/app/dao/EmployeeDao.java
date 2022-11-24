package com.yaroshevich.app.dao;

import com.yaroshevich.app.mapper.EmployeeMapper;
import com.yaroshevich.app.model.Employee;
import com.yaroshevich.app.util.DBConnector;
import com.yaroshevich.app.util.DBWorker;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDao {

    private final Connection connection = new DBConnector().getConnection();

    public List<Employee> getAll() throws SQLException {
        DBWorker dbWorker = new DBWorker(connection);
        EmployeeMapper mapper = new EmployeeMapper();

        return mapper.map(dbWorker.executeQuery("SELECT * FROM employees"));
    }

}
