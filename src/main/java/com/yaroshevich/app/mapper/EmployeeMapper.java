package com.yaroshevich.app.mapper;

import com.yaroshevich.app.model.Address;
import com.yaroshevich.app.model.District;
import com.yaroshevich.app.model.Employee;
import com.yaroshevich.app.model.Shift;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeMapper implements Mapper<Employee> {

    public List<Employee> map(ResultSet resultSet) throws SQLException {
        List<Employee> list = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("employee_id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String patronymic = resultSet.getString("patronymic");
            int age = resultSet.getInt("age");

            int addressId = resultSet.getInt("address_id");
            String address = resultSet.getString("address");

            int districtId = resultSet.getInt("district_id");
            String districtName = resultSet.getString("district_name");
            Integer districtParentId = resultSet.getInt("district_parent_id");
            String districtParentName = resultSet.getString("district_parent");

            Integer shiftId = resultSet.getInt("shift_id");
            String startAt = resultSet.getString("start_at");
            String endAt = resultSet.getString("end_at");

            District parent;
            Shift shiftObj;

            if (districtParentId == null) {
                parent = null;
            } else {
                parent = new District(districtParentId, districtParentName);
            }

            if (shiftId == null) {
                shiftObj = null;
            } else {
                shiftObj = new Shift(shiftId, startAt, endAt);
            }

            District districtObj = new District(districtId, districtName, parent);
            Address addressObj = new Address(addressId, address, districtObj);


            Employee employee = new Employee(id, firstName, lastName, patronymic, age, addressObj, shiftObj);

            list.add(employee);
        }

        return list;
    }

    public Map<Integer, Integer> mapAges(ResultSet resultSet) throws SQLException {
        Map<Integer, Integer> result = new HashMap<>();

        while (resultSet.next()) {
            int age = resultSet.getInt("age");
            int count = resultSet.getInt("count");

            result.put(age, count);
        }

        return result;
    }

    public int mapCount(ResultSet resultSet) throws SQLException {
        int result = 0;

        while (resultSet.next()) {
            result = resultSet.getInt("employee_count");
        }

        return result;
    }

}
