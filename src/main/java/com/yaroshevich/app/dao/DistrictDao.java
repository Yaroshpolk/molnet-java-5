package com.yaroshevich.app.dao;

import com.yaroshevich.app.mapper.DistrictMapper;
import com.yaroshevich.app.model.District;
import com.yaroshevich.app.util.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DistrictDao {

    private static final String DISTRICT_SQL = "SELECT\n" +
            "    districts.id as district_id,\n" +
            "    districts.district_name,\n" +
            "    districts.parent_id,\n" +
            "    d2.district_name as district_parent\n" +
            "    FROM districts\n" +
            "        JOIN districts d on d.id = districts.id\n" +
            "        JOIN districts d2 on d2.id = d.id\n";


    public List<District> getAll() throws SQLException {
        try (Connection connection = DBConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DISTRICT_SQL);

            DistrictMapper mapper = new DistrictMapper();
            List<District> resultList = mapper.map(statement.executeQuery());

            return resultList;
        }
    }

    public District getById(int id) throws SQLException {
        try (Connection connection = DBConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DISTRICT_SQL + "WHERE districts.id = ?");
            statement.setInt(1, id);

            DistrictMapper mapper = new DistrictMapper();
            List<District> resultList = mapper.map(statement.executeQuery());

            return resultList.size() > 0 ? resultList.get(0) : null;
        }
    }

    public List<District> getDistricts() throws SQLException {
        try (Connection connection = DBConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    DISTRICT_SQL + "WHERE districts.parent_id IS NULL");

            DistrictMapper mapper = new DistrictMapper();
            List<District> resultList = mapper.map(statement.executeQuery());

            return resultList;
        }
    }

    public List<District> getRegions() throws SQLException {
        try (Connection connection = DBConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    DISTRICT_SQL + "WHERE districts.parent_id  IS NOT NULL");

            DistrictMapper mapper = new DistrictMapper();
            List<District> resultList = mapper.map(statement.executeQuery());

            return resultList;
        }
    }
}
