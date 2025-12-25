package org.model;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

public class User {
    private Integer userId;
    private String email;
    private String phone;
    private String passwordHash;
    private Timestamp createdAt;
    private boolean isDeleted = false;

    public User(Integer userId, String email, String phone, String passwordHash) {
        this.userId = userId;
        this.email = email;
        this.phone = phone;
        this.passwordHash = passwordHash;
        this.createdAt = Timestamp.from(Instant.now());
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isDeleted == user.isDeleted && Objects.equals(userId, user.userId) && Objects.equals(email, user.email) && Objects.equals(phone, user.phone) && Objects.equals(passwordHash, user.passwordHash) && Objects.equals(createdAt, user.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, phone, passwordHash, createdAt, isDeleted);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", createdAt=" + createdAt +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
