package com.yaroshevich.app.dao;

import com.yaroshevich.app.mapper.AddressMapper;
import com.yaroshevich.app.model.Address;
import com.yaroshevich.app.util.DBConnector;
import com.yaroshevich.app.util.DBWorker;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AddressDao implements Dao<Address> {

    private final Connection connection = new DBConnector().getConnection();

    public List<Address> getAll() throws SQLException {
        DBWorker dbWorker = new DBWorker(connection);
        AddressMapper mapper = new AddressMapper();

        return mapper.map(dbWorker.executeQuery("SELECT * FROM home_addresses"));

    }

}
