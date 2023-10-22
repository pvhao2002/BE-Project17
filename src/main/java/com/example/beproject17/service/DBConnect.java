package com.example.beproject17.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    static final String URL_DB = "jdbc:mysql://localhost:3306/project17?sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false";
    static final String USER = "root";
    static final String PASS = "";

    public static Connection getConnection() {
        Connection cnt = null;
        try {
            cnt = DriverManager.getConnection(URL_DB, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cnt;
    }
}
