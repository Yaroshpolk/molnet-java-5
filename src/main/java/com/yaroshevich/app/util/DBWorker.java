package com.yaroshevich.app.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBWorker {

    private final Statement statement;

    public DBWorker(Connection connection) {
        try {
            this.statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet executeQuery(String query) {
        ResultSet res = null;
        try {
            res = this.statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

}
