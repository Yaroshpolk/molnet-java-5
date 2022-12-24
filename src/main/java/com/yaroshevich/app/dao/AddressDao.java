package com.yaroshevich.app.dao;

import com.yaroshevich.app.mapper.AddressMapper;
import com.yaroshevich.app.model.Address;
import com.yaroshevich.app.util.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AddressDao implements Dao<Address> {

    public List<Address> getAll() throws SQLException {
        Connection connection = DBConnector.getConnection();
        Statement statement = connection.createStatement();

        AddressMapper mapper = new AddressMapper();

        List<Address> resultList = mapper.map(statement.executeQuery(
                "SELECT home_addresses.id            as address_id,\n" +
                        "       address,\n" +
                        "       d.id             as district_id,\n" +
                        "       d.district_name,\n" +
                        "       d.parent_id      as district_parent_id,\n" +
                        "       d2.district_name as district_parent\n" +
                        "FROM home_addresses\n" +
                        "         JOIN districts d on d.id = home_addresses.district_id\n" +
                        "         JOIN districts d2 on d2.id = home_addresses.district_id"));

        connection.close();
        return resultList;
    }

    @Override
    public Address getById(int id) throws SQLException {
        Connection connection = DBConnector.getConnection();
        Statement statement = connection.createStatement();

        AddressMapper mapper = new AddressMapper();

        List<Address> resultList = mapper.map(statement.executeQuery(
                "SELECT home_addresses.id            as address_id,\n" +
                        "       address,\n" +
                        "       d.id             as district_id,\n" +
                        "       d.district_name,\n" +
                        "       d.parent_id      as district_parent_id,\n" +
                        "       d2.district_name as district_parent\n" +
                        "FROM home_addresses\n" +
                        "         JOIN districts d on d.id = home_addresses.district_id\n" +
                        "         JOIN districts d2 on d2.id = home_addresses.district_id\n" +
                        "WHERE home_addresses.id =" + id));

        connection.close();
        return resultList.size() > 0 ? resultList.get(0) : null;
    }

    public Address getByParams(Address address) throws SQLException {
        Connection connection = DBConnector.getConnection();
        Statement statement = connection.createStatement();

        AddressMapper mapper = new AddressMapper();

        List<Address> resultList = mapper.map(statement.executeQuery(
                "SELECT home_addresses.id            as address_id,\n" +
                        "       address,\n" +
                        "       d.id             as district_id,\n" +
                        "       d.district_name,\n" +
                        "       d.parent_id      as parent_id,\n" +
                        "       d2.district_name as district_parent\n" +
                        "FROM home_addresses\n" +
                        "         JOIN districts d on d.id = home_addresses.district_id\n" +
                        "         JOIN districts d2 on d2.id = home_addresses.district_id\n" +
                        "WHERE address='" + address.getAddress() + "' and district_id=" + address.getDistrict().getId()));

        connection.close();
        return resultList.size() > 0 ? resultList.get(0) : null;
    }

    public Address add(Address address) throws SQLException {
        Connection connection = DBConnector.getConnection();
        Statement statement = connection.createStatement();

        statement.executeUpdate("INSERT INTO home_addresses(address, district_id) VALUES ('"
                + address.getAddress() + "'," + address.getDistrict().getId() + ")", Statement.RETURN_GENERATED_KEYS);

        int id = 1;

        try (ResultSet rows = statement.getGeneratedKeys()) {
            while (rows.next()) {
                id = rows.getInt(1);
            }
        }

        connection.close();
        return getById(id);
    }

}
