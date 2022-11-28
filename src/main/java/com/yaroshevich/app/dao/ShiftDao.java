package com.yaroshevich.app.dao;

import com.yaroshevich.app.mapper.ShiftMapper;
import com.yaroshevich.app.model.Shift;
import com.yaroshevich.app.util.DBConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ShiftDao implements Dao<Shift> {

    private final Connection connection = new DBConnector().getConnection();

    @Override
    public List<Shift> getAll() throws SQLException {
        Statement statement = connection.createStatement();
        ShiftMapper mapper = new ShiftMapper();

        return mapper.map(statement.executeQuery("SELECT * FROM shifts"));
    }

    @Override
    public Shift getById(int id) throws SQLException {
        return null;
    }
}
