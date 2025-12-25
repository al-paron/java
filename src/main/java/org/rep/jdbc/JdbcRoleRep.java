package org.rep.jdbc;

import org.model.Role;
import org.rep.RoleRep;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JdbcRoleRep extends JdbcBaseRep<Role> implements RoleRep {

    private Role mapRole(ResultSet rs) throws SQLException {
        Role role = new Role(
                rs.getInt("role_id"),
                rs.getInt("user_id"),
                rs.getString("role"),
                rs.getBoolean("is_deleted")
        );
        role.setCreatedAt(rs.getTimestamp("created_at"));
        return role;
    }

    @Override
    public Optional<Role> findSingle(Integer id) {
        String sql = "SELECT * FROM roles WHERE role_id = ? AND is_deleted = false";
        List<Role> roles = executeQuery(sql, this::mapRole, id);
        return roles.stream().findFirst();
    }

    @Override
    public List<Role> findAll() {
        String sql = "SELECT * FROM roles WHERE is_deleted = false";
        return executeQuery(sql, this::mapRole);
    }

    @Override
    public void save(Role role) {
        if (role.getRoleId() == null) {
            String sql = "INSERT INTO roles (user_id, role, created_at, is_deleted) VALUES (?, ?, ?, ?)";
            Integer generatedId = executeInsert(sql,
                    role.getUserId(),
                    role.getRole(),
                    role.getCreatedAt(),
                    role.isDeleted()
            );
            if (generatedId != null) {
                role.setRoleId(generatedId);
            }
        } else {
            String sql = "UPDATE roles SET user_id = ?, role = ?, created_at = ?, is_deleted = ? WHERE role_id = ?";
            executeUpdate(sql,
                    role.getUserId(),
                    role.getRole(),
                    role.getCreatedAt(),
                    role.isDeleted(),
                    role.getRoleId()
            );
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "UPDATE roles SET is_deleted = true WHERE role_id = ?";
        executeUpdate(sql, id);
    }

    @Override
    public List<Role> findByUserId(Integer userId) {
        String sql = "SELECT * FROM roles WHERE user_id = ? AND is_deleted = false";
        return executeQuery(sql, this::mapRole, userId);
    }

    @Override
    public boolean isUserSeller(Integer userId) {
        String sql = "SELECT COUNT(*) as count FROM roles WHERE user_id = ? AND role = 'SELLER' AND is_deleted = false";
        List<Integer> counts = executeScalarQuery(sql, rs -> rs.getInt("count"), userId);
        return !counts.isEmpty() && counts.get(0) > 0;
    }
}