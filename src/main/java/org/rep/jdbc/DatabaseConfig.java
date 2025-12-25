package org.rep.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {
    private static final String URL = "jdbc:postgresql://localhost:5432/farm_market";
    private static final String USER = "postgres";
    private static final String PASSWORD = "password";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL driver not found. Make sure PostgreSQL JDBC driver is in the classpath.");
            throw new RuntimeException("PostgreSQL driver not found", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            if (connection == null || connection.isClosed()) {
                Properties props = new Properties();
                props.setProperty("user", USER);
                props.setProperty("password", PASSWORD);
                props.setProperty("ssl", "false");
                connection = DriverManager.getConnection(URL, props);
            }
            return connection;
        } catch (SQLException e) {
            System.err.println("Failed to get database connection: " + e.getMessage());
            throw e;
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing database connection: " + e.getMessage());
            }
        }
    }

    public static void testConnection() {
        try {
            Connection conn = getConnection();
            if (conn != null && !conn.isClosed()) {
                System.out.println("Database connection test: SUCCESS");
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println("Database connection test: FAILED - " + e.getMessage());
        }
    }
}