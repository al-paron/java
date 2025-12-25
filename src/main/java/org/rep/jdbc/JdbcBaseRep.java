package org.rep.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class JdbcBaseRep<T> {
    protected Connection getConnection() {
        try {
            return DatabaseConfig.getConnection();
        } catch (SQLException e) {
            System.err.println("Error getting database connection: " + e.getMessage());
            return null;
        }
    }

    protected void closeResources(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.err.println("Error closing resources: " + e.getMessage());
        }
    }

    protected void closeResources(Connection conn, Statement stmt) {
        closeResources(conn, stmt, null);
    }

    protected boolean executeUpdate(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            if (conn == null) return false;

            stmt = conn.prepareStatement(sql);

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error executing update: " + e.getMessage());
            return false;
        } finally {
            closeResources(conn, stmt);
        }
    }

    protected Integer executeInsert(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            if (conn == null) return null;

            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                return rs.getInt(1);
            }
            return null;
        } catch (SQLException e) {
            System.err.println("Error executing insert: " + e.getMessage());
            return null;
        } finally {
            closeResources(conn, stmt, rs);
        }
    }

    protected List<T> executeQuery(String sql, ResultSetMapper<T> mapper, Object... params) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<T> result = new ArrayList<>();

        try {
            conn = getConnection();
            if (conn == null) return result;

            stmt = conn.prepareStatement(sql);

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            rs = stmt.executeQuery();

            while (rs.next()) {
                result.add(mapper.map(rs));
            }

            return result;
        } catch (SQLException e) {
            System.err.println("Error executing query: " + e.getMessage());
            return result;
        } finally {
            closeResources(conn, stmt, rs);
        }
    }

    protected <R> List<R> executeScalarQuery(String sql, ScalarMapper<R> mapper, Object... params) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<R> result = new ArrayList<>();

        try {
            conn = getConnection();
            if (conn == null) return result;

            stmt = conn.prepareStatement(sql);

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            rs = stmt.executeQuery();

            while (rs.next()) {
                result.add(mapper.map(rs));
            }

            return result;
        } catch (SQLException e) {
            System.err.println("Error executing scalar query: " + e.getMessage());
            return result;
        } finally {
            closeResources(conn, stmt, rs);
        }
    }

    protected interface ResultSetMapper<T> {
        T map(ResultSet rs) throws SQLException;
    }

    protected interface ScalarMapper<R> {
        R map(ResultSet rs) throws SQLException;
    }
}