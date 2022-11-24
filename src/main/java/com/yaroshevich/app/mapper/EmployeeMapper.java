package com.yaroshevich.app.mapper;

import com.yaroshevich.app.model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeMapper {

    public static List<Employee> map(ResultSet resultSet) throws SQLException {
        List<Employee> list = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String patronymic = resultSet.getString("patronymic");
            int age = resultSet.getInt("age");
            int addressId = resultSet.getInt("address_id");
            int shiftId = resultSet.getInt("shift_id");

            Employee employee = new Employee(id, firstName, lastName, patronymic, age, addressId, shiftId);
            list.add(employee);
        }

        return list;
    }

}
