package org.rep.jdbc;

import org.model.Review;
import org.rep.ReviewRep;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JdbcReviewRep extends JdbcBaseRep<Review> implements ReviewRep {

    private Review mapReview(ResultSet rs) throws SQLException {
        Review review = new Review(
                rs.getInt("review_id"),
                rs.getInt("user_id"),
                rs.getInt("product_id"),
                rs.getInt("rating"),
                rs.getString("comment")
        );
        review.setCreatedAt(rs.getTimestamp("created_at"));
        review.setDeleted(rs.getBoolean("is_deleted"));
        return review;
    }

    @Override
    public Optional<Review> findSingle(Integer id) {
        String sql = "SELECT * FROM reviews WHERE review_id = ? AND is_deleted = false";
        List<Review> reviews = executeQuery(sql, this::mapReview, id);
        return reviews.stream().findFirst();
    }

    @Override
    public List<Review> findAll() {
        String sql = "SELECT * FROM reviews WHERE is_deleted = false";
        return executeQuery(sql, this::mapReview);
    }

    @Override
    public void save(Review review) {
        if (review.getReviewId() == null) {
            String sql = "INSERT INTO reviews (user_id, product_id, rating, comment, created_at) VALUES (?, ?, ?, ?, ?)";
            Integer generatedId = executeInsert(sql,
                    review.getUserId(),
                    review.getProductId(),
                    review.getRating(),
                    review.getComment(),
                    review.getCreatedAt()
            );
            if (generatedId != null) {
                review.setReviewId(generatedId);
            }
        } else {
            String sql = "UPDATE reviews SET user_id = ?, product_id = ?, rating = ?, comment = ?, created_at = ?, is_deleted = ? WHERE review_id = ?";
            executeUpdate(sql,
                    review.getUserId(),
                    review.getProductId(),
                    review.getRating(),
                    review.getComment(),
                    review.getCreatedAt(),
                    review.isDeleted(),
                    review.getReviewId()
            );
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "UPDATE reviews SET is_deleted = true WHERE review_id = ?";
        executeUpdate(sql, id);
    }

    @Override
    public List<Review> findByProductId(Integer productId) {
        String sql = "SELECT * FROM reviews WHERE product_id = ? AND is_deleted = false";
        return executeQuery(sql, this::mapReview, productId);
    }

    @Override
    public List<Review> findByUserId(Integer userId) {
        String sql = "SELECT * FROM reviews WHERE user_id = ? AND is_deleted = false";
        return executeQuery(sql, this::mapReview, userId);
    }
}