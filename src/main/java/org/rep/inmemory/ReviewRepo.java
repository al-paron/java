package org.rep.inmemory;

import org.model.Review;
import org.rep.ReviewRep;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ReviewRepo implements ReviewRep {
    private final Map<Integer, Review> data = new HashMap<>();

    @Override
    public Optional<Review> findSingle(Integer id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<Review> findAll() {
        return data.values().stream()
                .filter(review -> !review.isDeleted())
                .toList();
    }

    @Override
    public void save(Review object) {
        data.put(object.getReviewId(), object);
    }

    @Override
    public void delete(Integer id) {
        Review review = data.get(id);
        if (review != null) {
            review.setDeleted(true);
            data.put(id, review);
        }
    }

    public List<Review> findByProductId(Integer productId) {
        return data.values().stream()
                .filter(review -> !review.isDeleted()
                        && review.getProductId() != null
                        && review.getProductId().equals(productId))
                .toList();
    }

    public List<Review> findByUserId(Integer userId) {
        return data.values().stream()
                .filter(review -> !review.isDeleted()
                        && review.getUserId().equals(userId))
                .toList();
    }
}