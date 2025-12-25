package org.service.inmemory;

import org.model.Product;
import org.rep.inmemory.ProductRepo;
import org.service.ProductInterface;

import java.util.List;
import java.util.Optional;

public class ProductService implements ProductInterface {
    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Optional<Product> findSingle(Integer id) {
        return productRepo.findSingle(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public void save(Product product) {
        productRepo.save(product);
    }

    @Override
    public void delete(Integer id) {
        productRepo.delete(id);
    }

    // Дополнительные методы
    public List<Product> findBySellerId(Integer sellerId) {
        return productRepo.findBySellerId(sellerId);
    }

    public List<Product> findByCategoryId(Integer categoryId) {
        return productRepo.findByCategoryId(categoryId);
    }
}