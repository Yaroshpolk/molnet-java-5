package com.yaroshevich.app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private Connection connection;

    private final String url;

    private final String userName;

    private final String userPassword;

    private final String driverName;

    public DBConnector() {

        this.url = PropertiesHelper.properties.getProperty("db.url");
        this.driverName = PropertiesHelper.properties.getProperty("db.driver");
        this.userName = PropertiesHelper.properties.getProperty("db.user");
        this.userPassword = PropertiesHelper.properties.getProperty("db.password");

        startConnection();
    }

    public Connection getConnection() {
        return this.connection;
    }

    private void startConnection() {
        try {
            Class.forName(driverName);
            this.connection = DriverManager.getConnection(this.url, this.userName, this.userPassword);
            System.out.println("Connection completed");
        } catch (ClassNotFoundException err) {
            System.out.println("Missing JDBC driver...");
            err.printStackTrace();
        } catch (SQLException err) {
            System.out.println("Db connection is failed...");
            if (err.getErrorCode() == 1045) {
                System.out.println("Access denied.");
            }
        }
    }


}
