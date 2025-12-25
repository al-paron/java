package org.service;

import org.model.Seller;

import java.util.Optional;

public interface SellerInterface extends BaseInterface<Seller, Integer> {
    Optional<Seller> findByUserId(Integer userId);
}