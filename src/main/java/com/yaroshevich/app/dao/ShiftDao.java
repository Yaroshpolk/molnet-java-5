package com.yaroshevich.app.dao;

import com.yaroshevich.app.mapper.ShiftMapper;
import com.yaroshevich.app.model.Shift;
import com.yaroshevich.app.util.DBConnector;
import com.yaroshevich.app.util.DBWorker;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ShiftDao implements Dao<Shift> {

    private final Connection connection = new DBConnector().getConnection();

    @Override
    public List<Shift> getAll() throws SQLException {
        DBWorker dbWorker = new DBWorker(connection);
        ShiftMapper mapper = new ShiftMapper();

        return mapper.map(dbWorker.executeQuery("SELECT * FROM shifts"));
    }
}
