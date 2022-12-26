package com.yaroshevich.app.mapper;

import com.yaroshevich.app.model.Address;
import com.yaroshevich.app.model.District;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressMapper implements Mapper<Address> {

    public List<Address> map(ResultSet resultSet) throws SQLException {
        List<Address> list = new ArrayList<>();
        DistrictMapper districtMapper = new DistrictMapper();

        while (resultSet.next()) {
            int id = resultSet.getInt("address_id");
            String address = resultSet.getString("address");

            District district = districtMapper.map(resultSet).size() > 0 ? districtMapper.map(resultSet).get(0) : null;

            Address addressObj = new Address(id, address, district);

            list.add(addressObj);
        }

        return list;
    }

}
