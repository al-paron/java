package org.rep;

import org.model.Review;

import java.util.List;

public interface ReviewRep extends BaseRep<Review, Integer> {
    List<Review> findByProductId(Integer productId);
    List<Review> findByUserId(Integer userId);
}