package com.yaroshevich.app.dao;

import com.yaroshevich.app.mapper.DistrictMapper;
import com.yaroshevich.app.model.District;
import com.yaroshevich.app.util.DBConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DistrictDao implements Dao<District> {

    @Override
    public List<District> getAll() throws SQLException {
        Connection connection = DBConnector.getConnection();
        Statement statement = connection.createStatement();

        DistrictMapper mapper = new DistrictMapper();

        List<District> resultList = mapper.map(statement.executeQuery("SELECT\n" +
                "    districts.id as district_id,\n" +
                "    districts.district_name,\n" +
                "    districts.parent_id,\n" +
                "    d2.district_name as district_parent\n" +
                "    FROM districts\n" +
                "        JOIN districts d on d.id = districts.id\n" +
                "        JOIN districts d2 on d2.id = d.id\n"));

        connection.close();
        return resultList;
    }

    @Override
    public District getById(int id) throws SQLException {
        Connection connection = DBConnector.getConnection();
        Statement statement = connection.createStatement();

        DistrictMapper mapper = new DistrictMapper();

        List<District> resultList = mapper.map(statement.executeQuery("SELECT\n" +
                "    districts.id as district_id,\n" +
                "    districts.district_name,\n" +
                "    districts.parent_id,\n" +
                "    d2.district_name as district_parent\n" +
                "    FROM districts\n" +
                "        JOIN districts d on d.id = districts.id\n" +
                "        JOIN districts d2 on d2.id = d.id\n" +
                "    where districts.id=" + id));

        connection.close();
        return resultList.get(0);
    }

    public List<District> getDistricts() throws SQLException {
        Connection connection = DBConnector.getConnection();
        Statement statement = connection.createStatement();

        DistrictMapper mapper = new DistrictMapper();

        List<District> resultList = mapper.map(statement.executeQuery(
                "SELECT districts.id as district_id,\n" +
                        "       districts.district_name,\n" +
                        "       districts.parent_id,\n" +
                        "       d2.district_name as district_parent\n" +
                        "FROM districts\n" +
                        "         JOIN districts d on d.id = districts.id\n" +
                        "         JOIN districts d2 on d2.id = d.id\n" +
                        "where districts.parent_id IS NULL "));

        return resultList;
    }

    public List<District> getRegions() throws SQLException {
        Connection connection = DBConnector.getConnection();
        Statement statement = connection.createStatement();

        DistrictMapper mapper = new DistrictMapper();

        List<District> resultList = mapper.map(statement.executeQuery(
                "SELECT districts.id as district_id,\n" +
                        "       districts.district_name,\n" +
                        "       districts.parent_id,\n" +
                        "       d2.district_name as district_parent\n" +
                        "FROM districts\n" +
                        "         JOIN districts d on d.id = districts.id\n" +
                        "         JOIN districts d2 on d2.id = d.id\n" +
                        "where districts.parent_id  IS NOT NULL "));

        return resultList;
    }
}
