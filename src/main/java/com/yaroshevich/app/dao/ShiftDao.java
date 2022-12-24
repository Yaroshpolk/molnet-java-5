package com.yaroshevich.app.dao;

import com.yaroshevich.app.mapper.ShiftMapper;
import com.yaroshevich.app.model.Shift;
import com.yaroshevich.app.util.DBConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ShiftDao implements Dao<Shift> {

    @Override
    public List<Shift> getAll() throws SQLException {
        Connection connection = DBConnector.getConnection();
        Statement statement = connection.createStatement();
        ShiftMapper mapper = new ShiftMapper();

        List<Shift> resultList = mapper.map(statement.executeQuery("SELECT * FROM shifts"));

        connection.close();
        return resultList;
    }

    @Override
    public Shift getById(int id) throws SQLException {
        Connection connection = DBConnector.getConnection();
        Statement statement = connection.createStatement();
        ShiftMapper mapper = new ShiftMapper();

        List<Shift> resultList = mapper.map(statement.executeQuery("SELECT * FROM shifts WHERE id = " + id));

        connection.close();
        return resultList.size() > 0 ? resultList.get(0) : null;
    }

    public Shift getByTime(Shift shift) throws SQLException {
        Connection connection = DBConnector.getConnection();
        Statement statement = connection.createStatement();
        ShiftMapper mapper = new ShiftMapper();

        System.out.println(shift.getEnd());

        List<Shift> resultList = mapper.map(statement.executeQuery("SELECT * FROM shifts WHERE start_at = '"
                + shift.getStart() + "' and end_at = '" + shift.getEnd() + "'"));

        connection.close();
        return resultList.size() > 0 ? resultList.get(0) : null;
    }

    public Shift add(Shift shift) throws SQLException {
        Connection connection = DBConnector.getConnection();
        Statement statement = connection.createStatement();

        statement.executeUpdate("INSERT INTO shifts(start_at, end_at) VALUES ('"
                + shift.getStart() + "', '" + shift.getEnd() + "')");

        connection.close();

        return getByTime(shift);
    }
}
