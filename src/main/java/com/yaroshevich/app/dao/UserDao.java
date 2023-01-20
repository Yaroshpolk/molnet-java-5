package com.yaroshevich.app.dao;

import com.yaroshevich.app.mapper.UserMapper;
import com.yaroshevich.app.model.User;
import com.yaroshevich.app.util.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {

    private static final String ADD_USER_SQL = " INSERT INTO users(login, password, user_name) " +
            "VALUES (?, ?, ?)";

    private static final String SELECT_USER_SQL = " SELECT * FROM users WHERE id = ?";

    private static final String SELECT_WITH_LOGINS_SQL = " SELECT COUNT(*) FROM users WHERE login = ?";

    private static final String SELECT_USERS_BY_LOGIN_SQL = " SELECT * FROM users WHERE login = ?";

    public User add(User user) throws SQLException {
        try (Connection connection = DBConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(ADD_USER_SQL,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());


            int res = statement.executeUpdate();

            int id = 1;

            try (ResultSet rows = statement.getGeneratedKeys()) {
                while (rows.next()) {
                    id = rows.getInt(1);
                }
            }

            return getById(id);
        }
    }

    public User getById(int id) throws SQLException {
        try (Connection connection = DBConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_SQL);
            statement.setInt(1, id);

            UserMapper mapper = new UserMapper();
            List<User> resultList = mapper.map(statement.executeQuery());

            return resultList.size() > 0 ? resultList.get(0) : null;
        }
    }

    public boolean isLoginAvailable(String login) throws SQLException {
        try (Connection connection = DBConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_WITH_LOGINS_SQL);
            statement.setString(1, login);

            ResultSet resultSet = statement.executeQuery();
            boolean res = false;

            while (resultSet.next()) {
                res = resultSet.getInt(1) <= 0;
            }

            return res;
        }
    }

    public User getByLogin(String login) throws SQLException {
        try (Connection connection = DBConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_USERS_BY_LOGIN_SQL);
            statement.setString(1, login);

            UserMapper mapper = new UserMapper();
            List<User> resultList = mapper.map(statement.executeQuery());

            return resultList.size() > 0 ? resultList.get(0) : null;
        }
    }

}
