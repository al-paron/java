package org.service;

import org.model.Product;

import java.util.List;

public interface ProductInterface extends BaseInterface<Product, Integer> {
    List<Product> findBySellerId(Integer sellerId);
    List<Product> findByCategoryId(Integer categoryId);
}