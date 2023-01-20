package com.yaroshevich.app.dao;

import com.yaroshevich.app.mapper.ShiftMapper;
import com.yaroshevich.app.model.Shift;
import com.yaroshevich.app.util.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.List;

public class ShiftDao {

    private static final String SHIFT_BY_ID_SQL = "SELECT * FROM shifts WHERE id = ?";

    private static final String SHIFT_BY_TIME_SQL = "SELECT * FROM shifts WHERE start_at = ? and end_at = ?";

    private static final String ADD_SHIFT_SQL = "INSERT INTO shifts(start_at, end_at) VALUES ( ?, ?)";


    public List<Shift> getAll() throws SQLException {
        try (Connection connection = DBConnector.getConnection()) {
            Statement statement = connection.createStatement();

            ShiftMapper mapper = new ShiftMapper();
            List<Shift> resultList = mapper.map(statement.executeQuery("SELECT * FROM shifts"));

            return resultList;
        }
    }

    public Shift getById(int id) throws SQLException {
        try (Connection connection = DBConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SHIFT_BY_ID_SQL);
            statement.setInt(1, id);

            ShiftMapper mapper = new ShiftMapper();
            List<Shift> resultList = mapper.map(statement.executeQuery());

            return resultList.size() > 0 ? resultList.get(0) : null;
        }
    }

    public Shift getByTime(Shift shift) throws SQLException {
        try (Connection connection = DBConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SHIFT_BY_TIME_SQL);
            statement.setObject(1, LocalTime.parse(shift.getStart()));
            statement.setObject(2, LocalTime.parse(shift.getEnd()));

            ShiftMapper mapper = new ShiftMapper();
            List<Shift> resultList = mapper.map(statement.executeQuery());

            return resultList.size() > 0 ? resultList.get(0) : null;
        }
    }

    public Shift add(Shift shift) throws SQLException {
        try (Connection connection = DBConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(ADD_SHIFT_SQL);
            statement.setObject(1, LocalTime.parse(shift.getStart()));
            statement.setObject(2, LocalTime.parse(shift.getEnd()));

            statement.executeUpdate();

            return getByTime(shift);
        }
    }
}
