package com.yaroshevich.app.mapper;

import com.yaroshevich.app.model.Shift;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShiftMapper implements Mapper<Shift> {

    @Override
    public List<Shift> map(ResultSet resultSet) throws SQLException {
        List<Shift> list = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String start = resultSet.getString("start_at");
            String end = resultSet.getString("end_at");

            Shift shift = new Shift(id, start, end);
            list.add(shift);
        }

        return list;
    }
}
