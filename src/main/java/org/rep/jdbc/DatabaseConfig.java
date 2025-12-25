package org.rep.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {

    private static final String URL = "jdbc:postgresql://localhost:5432/farm_market";

    private static final String USER = "postgres";

    private static final String PASSWORD = "postgres123";


    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("PostgreSQL driver registered successfully");
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: PostgreSQL driver not found.");
            System.err.println("Make sure PostgreSQL JDBC driver is in the classpath.");
            System.err.println("If using Maven, add dependency in pom.xml:");
            System.err.println("<dependency>");
            System.err.println("    <groupId>org.postgresql</groupId>");
            System.err.println("    <artifactId>postgresql</artifactId>");
            System.err.println("    <version>42.6.0</version>");
            System.err.println("</dependency>");
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

                System.out.println("Attempting to connect to: " + URL);
                System.out.println("Username: " + USER);

                connection = DriverManager.getConnection(URL, props);

                if (connection != null) {
                    System.out.println("Connected to database successfully!");
                }
            }
            return connection;
        } catch (SQLException e) {
            System.err.println("Failed to connect to database: " + e.getMessage());
            System.err.println("URL: " + URL);
            System.err.println("User: " + USER);
            System.err.println("Check: 1) Is PostgreSQL running? 2) Correct password? 3) Database exists?");
            throw e;
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed");
            } catch (SQLException e) {
                System.err.println("Error closing database connection: " + e.getMessage());
            }
        }
    }

    public static void testConnection() {
        System.out.println("\n=== Testing Database Connection ===");
        System.out.println("Driver: PostgreSQL JDBC");
        System.out.println("URL: " + URL);
        System.out.println("User: " + USER);

        Connection conn = null;
        try {
            conn = getConnection();
            if (conn != null && !conn.isClosed()) {
                System.out.println("✓ SUCCESS: Connected to database!");

                System.out.println("Database: " + conn.getCatalog());
                System.out.println("Auto-commit: " + conn.getAutoCommit());

                conn.close();
            }
        } catch (SQLException e) {
            System.err.println("✗ FAILED: " + e.getMessage());

            if (e.getMessage().contains("password authentication failed")) {
                System.err.println("Hint: Check username and password in DatabaseConfig.java");
            } else if (e.getMessage().contains("connection refused")) {
                System.err.println("Hint: Is PostgreSQL running? Check with: netstat -an | findstr 5432");
            } else if (e.getMessage().contains("database \"farm_market\" does not exist")) {
                System.err.println("Hint: Database doesn't exist. Create it: CREATE DATABASE farm_market;");
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
        System.out.println("=== Test Complete ===\n");
    }
}