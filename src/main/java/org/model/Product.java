package org.model;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

public class Product {
    private Integer productId;
    private Integer sellerId;
    private Integer categoryId;
    private String name;
    private String description;
    private Float pricePerUnit;
    private String unit;
    private Float quantity;
    private Timestamp lastUpdated;
    private Timestamp productionDate;
    private boolean isDeleted = false;

    public Product(Integer productId, Integer sellerId, Integer categoryId, String name, String description, Float pricePerUnit, String unit, Float quantity, Timestamp productionDate) {
        this.productId = productId;
        this.sellerId = sellerId;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.pricePerUnit = pricePerUnit;
        this.unit = unit;
        this.quantity = quantity;
        this.lastUpdated = Timestamp.from(Instant.now());
        this.productionDate = productionDate;
    }

    public Product(Integer productId, Integer sellerId, Integer categoryId, String name, Float pricePerUnit, String unit, Float quantity, Timestamp productionDate) {
        this.productId = productId;
        this.sellerId = sellerId;
        this.categoryId = categoryId;
        this.name = name;
        this.pricePerUnit = pricePerUnit;
        this.unit = unit;
        this.quantity = quantity;
        this.lastUpdated = Timestamp.from(Instant.now());
        this.productionDate = productionDate;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Float pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Timestamp getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Timestamp productionDate) {
        this.productionDate = productionDate;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return isDeleted == product.isDeleted && Objects.equals(productId, product.productId) && Objects.equals(sellerId, product.sellerId) && Objects.equals(categoryId, product.categoryId) && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(pricePerUnit, product.pricePerUnit) && Objects.equals(unit, product.unit) && Objects.equals(quantity, product.quantity) && Objects.equals(lastUpdated, product.lastUpdated) && Objects.equals(productionDate, product.productionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, sellerId, categoryId, name, description, pricePerUnit, unit, quantity, lastUpdated, productionDate, isDeleted);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", sellerId=" + sellerId +
                ", categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pricePerUnit=" + pricePerUnit +
                ", unit='" + unit + '\'' +
                ", quantity=" + quantity +
                ", lastUpdated=" + lastUpdated +
                ", productionDate=" + productionDate +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
