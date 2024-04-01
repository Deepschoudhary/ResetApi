package com.FirstApi.restapi.connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connectivity {

    public static final String URL = "jdbc:mysql://localhost:3306/course_details";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "Deeps#09";

    public static Connection getConnection() {

        Connection connection = null;
        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Open a connection
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        }
        catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace(); // Handle any errors

        }
        return connection;
    }

}
