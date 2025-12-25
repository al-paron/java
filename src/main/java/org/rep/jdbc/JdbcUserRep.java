package org.rep.jdbc;

import org.model.User;
import org.rep.UserRep;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JdbcUserRep extends JdbcBaseRep<User> implements UserRep {

    private User mapUser(ResultSet rs) throws SQLException {
        User user = new User(
                rs.getInt("user_id"),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getString("password_hash")
        );
        user.setCreatedAt(rs.getTimestamp("created_at"));
        user.setDeleted(rs.getBoolean("is_deleted"));
        return user;
    }

    @Override
    public Optional<User> findSingle(Integer id) {
        String sql = "SELECT * FROM users WHERE user_id = ? AND is_deleted = false";
        List<User> users = executeQuery(sql, this::mapUser, id);
        return users.stream().findFirst();
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM users WHERE is_deleted = false";
        return executeQuery(sql, this::mapUser);
    }

    @Override
    public void save(User user) {
        if (user.getUserId() == null) {
            // Insert new user
            String sql = "INSERT INTO users (email, phone, password_hash, created_at) VALUES (?, ?, ?, ?)";
            Integer generatedId = executeInsert(sql,
                    user.getEmail(),
                    user.getPhone(),
                    user.getPasswordHash(),
                    user.getCreatedAt()
            );
            if (generatedId != null) {
                user.setUserId(generatedId);
            }
        } else {
            // Update existing user
            String sql = "UPDATE users SET email = ?, phone = ?, password_hash = ?, created_at = ?, is_deleted = ? WHERE user_id = ?";
            executeUpdate(sql,
                    user.getEmail(),
                    user.getPhone(),
                    user.getPasswordHash(),
                    user.getCreatedAt(),
                    user.isDeleted(),
                    user.getUserId()
            );
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "UPDATE users SET is_deleted = true WHERE user_id = ?";
        executeUpdate(sql, id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ? AND is_deleted = false";
        List<User> users = executeQuery(sql, this::mapUser, email);
        return users.stream().findFirst();
    }

    @Override
    public boolean emailExists(String email) {
        String sql = "SELECT COUNT(*) as count FROM users WHERE email = ? AND is_deleted = false";
        List<Integer> counts = executeScalarQuery(sql, rs -> rs.getInt("count"), email);
        return !counts.isEmpty() && counts.get(0) > 0;
    }
}