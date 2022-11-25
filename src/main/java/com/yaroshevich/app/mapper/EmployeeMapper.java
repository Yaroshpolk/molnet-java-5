package com.yaroshevich.app.mapper;

import com.yaroshevich.app.dto.EmployeeDto;
import com.yaroshevich.app.model.Address;
import com.yaroshevich.app.model.Employee;
import com.yaroshevich.app.model.Shift;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeMapper implements Mapper<Employee> {

    public List<Employee> map(ResultSet resultSet) throws SQLException {
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

    public List<EmployeeDto> mapWithDetails(ResultSet resultSet) throws SQLException {
        List<EmployeeDto> list = new ArrayList<>();

        while (resultSet.next()) {

            int id = resultSet.getInt("employee_id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String patronymic = resultSet.getString("patronymic");
            int age = resultSet.getInt("age");

            int addressId = resultSet.getInt("address_id");
            String address = resultSet.getString("address");
            String district = resultSet.getString("district");
            String region = resultSet.getString("region");

            int shiftId = resultSet.getInt("shift_id");
            String startAt = resultSet.getString("start_at");
            String endAt = resultSet.getString("end_at");

            Address addressObj = new Address(addressId, address, district, region);
            Shift shiftObj = new Shift(shiftId, startAt, endAt);

            EmployeeDto employee = new EmployeeDto(id, firstName, lastName, patronymic, age, addressObj, shiftObj);
            list.add(employee);
        }

        return list;
    }

}
