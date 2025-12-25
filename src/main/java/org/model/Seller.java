package org.model;

import java.util.Objects;

public class Seller {
    private Integer sellerId;
    private Integer userId;
    private String farmName;
    private String description;
    private Integer rating;
    private boolean isDeleted = false;

    public Seller(Integer sellerId, Integer userId, String farmName, String description) {
        this.sellerId = sellerId;
        this.userId = userId;
        this.farmName = farmName;
        this.description = description;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return isDeleted == seller.isDeleted && Objects.equals(sellerId, seller.sellerId) && Objects.equals(userId, seller.userId) && Objects.equals(farmName, seller.farmName) && Objects.equals(description, seller.description) && Objects.equals(rating, seller.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sellerId, userId, farmName, description, rating, isDeleted);
    }

    @Override
    public String toString() {
        return "Seller{" +
                "sellerId=" + sellerId +
                ", userId=" + userId +
                ", farmName='" + farmName + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
