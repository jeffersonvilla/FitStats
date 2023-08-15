package com.fit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnection {

    private static Connection connection;

    private MysqlConnection() {}

    public static Connection getConnection() {
        if (connection == null) {
            synchronized (MysqlConnection.class) {
                if (connection == null) {
                    try {
                        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/FitStats?", "root", "root");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return connection;
    }
}

