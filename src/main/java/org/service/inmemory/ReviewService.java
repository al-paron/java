package org.service.inmemory;

import org.model.Review;
import org.rep.inmemory.ReviewRepo;
import org.service.ReviewInterface;

import java.util.List;
import java.util.Optional;

public class ReviewService implements ReviewInterface {
    private final ReviewRepo reviewRepo;

    public ReviewService(ReviewRepo reviewRepo) {
        this.reviewRepo = reviewRepo;
    }

    @Override
    public Optional<Review> findSingle(Integer id) {
        return reviewRepo.findSingle(id);
    }

    @Override
    public List<Review> findAll() {
        return reviewRepo.findAll();
    }

    @Override
    public void save(Review review) {
        reviewRepo.save(review);
    }

    @Override
    public void delete(Integer id) {
        reviewRepo.delete(id);
    }

    // Дополнительные методы
    public List<Review> findByProductId(Integer productId) {
        return reviewRepo.findByProductId(productId);
    }

    public List<Review> findBySellerId(Integer sellerId) {
        return reviewRepo.findBySellerId(sellerId);
    }

    public List<Review> findByCustomerId(Integer customerId) {
        return reviewRepo.findByCustomerId(customerId);
    }
}