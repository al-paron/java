package org.model;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

public class Review {
    private Integer reviewId;
    private Integer userId;
    private Integer productId;
    private Integer rating;
    private String comment;
    private Timestamp createdAt;
    private boolean isDeleted = false;

    public Review(Integer reviewId, Integer userId, Integer productId, Integer rating, String comment) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.productId = productId;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = Timestamp.from(Instant.now());
    }


    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
        Review review = (Review) o;
        return isDeleted == review.isDeleted && Objects.equals(reviewId, review.reviewId) && Objects.equals(userId, review.userId) && Objects.equals(productId, review.productId) && Objects.equals(rating, review.rating) && Objects.equals(comment, review.comment) && Objects.equals(createdAt, review.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewId, userId, productId, rating, comment, createdAt, isDeleted);
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", customerId=" + userId +
                ", sellerId=" + productId +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", createdAt=" + createdAt +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
