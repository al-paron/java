package org.model;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

public class Role {
    private Integer roleId;
    private Integer userId;
    private String role;
    private boolean isDeleted;
    private Timestamp createdAt;

    public Role(Integer roleId, Integer userId, String role, boolean isDeleted) {
        this.roleId = roleId;
        this.userId = userId;
        this.role = role;
        this.isDeleted = isDeleted;
        this.createdAt = Timestamp.from(Instant.now());
    }


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", userId=" + userId +
                ", role='" + role + '\'' +
                ", isDeleted=" + isDeleted +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return isDeleted == role1.isDeleted && Objects.equals(roleId, role1.roleId) && Objects.equals(userId, role1.userId) && Objects.equals(role, role1.role) && Objects.equals(createdAt, role1.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, userId, role, isDeleted, createdAt);
    }
}
