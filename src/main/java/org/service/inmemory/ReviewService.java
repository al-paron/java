package org.service.inmemory;

import org.model.Review;
import org.rep.ReviewRep;  // Изменяем импорт
import org.service.ReviewInterface;

import java.util.List;
import java.util.Optional;

public class ReviewService implements ReviewInterface {
    private final ReviewRep reviewRepo;  // Изменяем тип поля

    // Изменяем тип параметра конструктора
    public ReviewService(ReviewRep reviewRepo) {
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

    @Override
    public List<Review> findByProductId(Integer productId) {
        return reviewRepo.findByProductId(productId);
    }

    @Override
    public List<Review> findByUserId(Integer userId) {
        return reviewRepo.findByUserId(userId);
    }
}