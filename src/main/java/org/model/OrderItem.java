package org.model;


import java.util.Objects;

public class OrderItem {
    private Integer orderItemId;
    private Integer orderId;
    private Integer productId;
    private Float quantity;
    private Float priceAtTime;
    private boolean isDeleted = false;

    public OrderItem(Integer orderItemId, Integer orderId, Integer productId, Float quantity, Float priceAtTime) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.priceAtTime = priceAtTime;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Integer getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public Float getPriceAtTime() {
        return priceAtTime;
    }

    public void setPriceAtTime(Float priceAtTime) {
        this.priceAtTime = priceAtTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return isDeleted == orderItem.isDeleted && Objects.equals(orderItemId, orderItem.orderItemId) && Objects.equals(orderId, orderItem.orderId) && Objects.equals(productId, orderItem.productId) && Objects.equals(quantity, orderItem.quantity) && Objects.equals(priceAtTime, orderItem.priceAtTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderItemId, orderId, productId, quantity, priceAtTime, isDeleted);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId=" + orderItemId +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", priceAtTime=" + priceAtTime +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
