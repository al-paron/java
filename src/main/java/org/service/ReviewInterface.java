package org.service;

import org.model.Review;

import java.util.List;

public interface ReviewInterface extends BaseInterface<Review, Integer> {
    List<Review> findByProductId(Integer productId);
    List<Review> findByUserId(Integer userId);
}