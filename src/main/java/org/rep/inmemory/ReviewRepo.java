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

    // Дополнительные методы для бизнес-логики
    public List<Review> findByProductId(Integer productId) {
        return data.values().stream()
                .filter(review -> !review.isDeleted()
                        && review.getProductId() != null
                        && review.getProductId().equals(productId))
                .toList();
    }

    public List<Review> findBySellerId(Integer sellerId) {
        return data.values().stream()
                .filter(review -> !review.isDeleted()
                        && review.getSellerId() != null
                        && review.getSellerId().equals(sellerId))
                .toList();
    }

    public List<Review> findByCustomerId(Integer customerId) {
        return data.values().stream()
                .filter(review -> !review.isDeleted()
                        && review.getCustomerId().equals(customerId))
                .toList();
    }
}