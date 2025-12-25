package org.rep;

import org.model.Product;

import java.util.List;

public interface ProductRep extends BaseRep<Product, Integer> {
    List<Product> findBySellerId(Integer sellerId);
    List<Product> findByCategoryId(Integer categoryId);
}