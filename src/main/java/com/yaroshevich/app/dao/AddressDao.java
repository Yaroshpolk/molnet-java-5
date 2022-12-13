package com.yaroshevich.app.dao;

import com.yaroshevich.app.mapper.AddressMapper;
import com.yaroshevich.app.model.Address;
import com.yaroshevich.app.util.DBConnector;
import org.apache.commons.math3.analysis.function.Add;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AddressDao implements Dao<Address> {

    public List<Address> getAll() throws SQLException {
        Connection connection = DBConnector.getConnection();
        Statement statement = connection.createStatement();

        AddressMapper mapper = new AddressMapper();

        List<Address> resultList = mapper.map(statement.executeQuery("SELECT * FROM home_addresses"));

        connection.close();
        return resultList;

    }

    @Override
    public Address getById(int id) throws SQLException {
        return null;
    }

}
