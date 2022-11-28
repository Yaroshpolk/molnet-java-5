package com.yaroshevich.app.dao;

import com.yaroshevich.app.mapper.AddressMapper;
import com.yaroshevich.app.model.Address;
import com.yaroshevich.app.util.DBConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AddressDao implements Dao<Address> {

    private final Connection connection = new DBConnector().getConnection();

    public List<Address> getAll() throws SQLException {
        Statement statement = connection.createStatement();

        AddressMapper mapper = new AddressMapper();

        return mapper.map(statement.executeQuery("SELECT * FROM home_addresses"));

    }

    @Override
    public Address getById(int id) throws SQLException {
        return null;
    }

}
