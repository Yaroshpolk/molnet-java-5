package com.yaroshevich.app.dao;

import com.yaroshevich.app.mapper.AddressMapper;
import com.yaroshevich.app.model.Address;
import com.yaroshevich.app.util.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AddressDao {

    private static final String ADDRESS_SQL =
            "SELECT home_addresses.id as address_id,\n" +
                    "       address,\n" +
                    "       d.id as district_id,\n" +
                    "       d.district_name,\n" +
                    "       d.parent_id      as district_parent_id,\n" +
                    "       d2.district_name as district_parent\n" +
                    "FROM home_addresses\n" +
                    "         JOIN districts d on d.id = home_addresses.district_id\n" +
                    "         JOIN districts d2 on d2.id = home_addresses.district_id";

    private static final String ADDRESS_ADD_SQL = "INSERT INTO home_addresses(address, district_id) VALUES (?, ?)";

    public List<Address> getAll() throws SQLException {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(ADDRESS_SQL);

        AddressMapper mapper = new AddressMapper();
        List<Address> resultList = mapper.map(statement.executeQuery());

        connection.close();
        return resultList;
    }


    public Address getById(int id) throws SQLException {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(ADDRESS_SQL + " WHERE home_addresses.id = ?");
        statement.setInt(1, id);

        AddressMapper mapper = new AddressMapper();
        List<Address> resultList = mapper.map(statement.executeQuery());

        connection.close();
        return resultList.size() > 0 ? resultList.get(0) : null;
    }

    public Address getByParams(Address address) throws SQLException {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(ADDRESS_SQL + " WHERE address=? and district_id=?");
        statement.setString(1, address.getAddress());
        statement.setInt(2, address.getDistrict().getId());

        AddressMapper mapper = new AddressMapper();
        List<Address> resultList = mapper.map(statement.executeQuery());

        connection.close();
        return resultList.size() > 0 ? resultList.get(0) : null;
    }

    public Address add(Address address) throws SQLException {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                ADDRESS_ADD_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, address.getAddress());
        statement.setInt(2, address.getDistrict().getId());

        statement.executeUpdate();

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
