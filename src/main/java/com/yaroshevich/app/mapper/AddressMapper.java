package com.yaroshevich.app.mapper;

import com.yaroshevich.app.model.Address;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressMapper implements Mapper<Address> {

    public List<Address> map(ResultSet resultSet) throws SQLException {
        List<Address> list = new ArrayList<>();

        while (resultSet.next()) {
//            int id = resultSet.getInt("id");
//            String address = resultSet.getString("address");
//            String district = resultSet.getString("district");
//            String region = resultSet.getString("region");
//
//            Address addressObj = new Address(id, address);
//            list.add(addressObj);
        }

        return list;
    }

}
