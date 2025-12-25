package org.rep.inmemory;

import org.model.Product;
import org.rep.ProductRep;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductRepo implements ProductRep {
    private final Map<Integer, Product> data = new HashMap<>();

    @Override
    public Optional<Product> findSingle(Integer id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<Product> findAll() {
        return data.values().stream()
                .filter(product -> !product.isDeleted())
                .toList();
    }

    @Override
    public void save(Product object) {
        data.put(object.getProductId(), object);
    }

    @Override
    public void delete(Integer id) {
        Product product = data.get(id);
        if (product != null) {
            product.setDeleted(true);
            data.put(id, product);
        }
    }

    // метод для поиска товаров по продавцу
    public List<Product> findBySellerId(Integer sellerId) {
        return data.values().stream()
                .filter(product -> !product.isDeleted()
                        && product.getSellerId().equals(sellerId))
                .toList();
    }

    // метод для поиска товаров по категории
    public List<Product> findByCategoryId(Integer categoryId) {
        return data.values().stream()
                .filter(product -> !product.isDeleted()
                        && product.getCategoryId().equals(categoryId))
                .toList();
    }
}