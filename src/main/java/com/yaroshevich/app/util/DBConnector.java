package com.yaroshevich.app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static Connection connection;

    private static final String url;

    private static final String userName;

    private static final String userPassword;

    private static final String driverName;

    static {
        url = PropertiesHelper.properties.getProperty("db.url");
        driverName = PropertiesHelper.properties.getProperty("db.driver");
        userName = PropertiesHelper.properties.getProperty("db.user");
        userPassword = PropertiesHelper.properties.getProperty("db.password");
    }

    public static Connection getConnection() {
        startConnection();

        return connection;
    }

    private static void startConnection() {
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, userName, userPassword);
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
