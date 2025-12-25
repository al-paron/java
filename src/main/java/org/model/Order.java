package org.model;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

public class Order {
    private Integer orderId;
    private Integer customerId;
    private String addressLine;
    private String status;
    private Timestamp createdAt;
    private boolean isDeleted = false;

    public Order(Integer orderId, Integer customerId, String addressLine, String status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.addressLine = addressLine;
        this.status = status;
        this.createdAt = Timestamp.from(Instant.now());
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        Order order = (Order) o;
        return isDeleted == order.isDeleted && Objects.equals(orderId, order.orderId) && Objects.equals(customerId, order.customerId) && Objects.equals(addressLine, order.addressLine) && Objects.equals(status, order.status) && Objects.equals(createdAt, order.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, customerId, addressLine, status, createdAt, isDeleted);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", addressLine='" + addressLine + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
