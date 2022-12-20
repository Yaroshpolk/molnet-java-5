package com.yaroshevich.app.mapper;

import com.yaroshevich.app.model.District;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DistrictMapper {

    public List<District> map(ResultSet resultSet) throws SQLException {
        List<District> list = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("district_id");
            String districtName = resultSet.getString("district_name");
            Integer parentId = resultSet.getInt("parent_id");
            String parentName;
            District parent;

            if (parentId == null) {
                parent = null;
            } else {
                parentName = resultSet.getString("district_parent");
                parent = new District(parentId, parentName);
            }

            District district = new District(id, districtName, parent);

            list.add(district);
        }

        return list;
    }

}
