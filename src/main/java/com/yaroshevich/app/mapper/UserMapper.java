package com.yaroshevich.app.mapper;

import com.yaroshevich.app.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMapper implements Mapper<User> {

    public List<User> map(ResultSet resultSet) throws SQLException {
        List<User> list = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");
            String name = resultSet.getString("user_name");

            User user = new User(id, name, login, password);
            list.add(user);
        }

        return list;
    }

}
