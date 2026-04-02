
package com.finlogic.employee.config;

import java.sql.*;


public class DBConnection {

    public static Connection getConnection() {
        Connection conn = null;

        try {
            // Read database info from Railway environment variables
            String dbHost = System.getenv("DB_HOST");
            String dbPort = System.getenv("DB_PORT");
            String dbName = System.getenv("DB_NAME");
            String dbUser = System.getenv("DB_USER");
            String dbPass = System.getenv("DB_PASS");

            // Build JDBC URL dynamically
            String url = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?sslMode=VERIFY_IDENTITY";

            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to DB
            conn = DriverManager.getConnection(url, dbUser, dbPass);

            System.out.println("DBConnection successful!");

        } catch (Exception e) {
            System.out.println("ERROR in DBConnection: " + e);
            e.printStackTrace();
        }

        return conn;
    }
}
