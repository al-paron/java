package org.rep;

import org.model.Seller;

import java.util.Optional;

public interface SellerRep extends BaseRep<Seller, Integer> {
    Optional<Seller> findByUserId(Integer userId);
}