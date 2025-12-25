package org.rep.inmemory;

import org.model.Category;
import org.rep.CategoryRep;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CategoryRepo implements CategoryRep {
    private final Map<Integer, Category> data = new HashMap<>();

    @Override
    public Optional<Category> findSingle(Integer id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<Category> findAll() {
        return data.values().stream()
                .filter(category -> !category.isDeleted())
                .toList();
    }

    @Override
    public void save(Category object) {
        data.put(object.getCategoryId(), object);
    }

    @Override
    public void delete(Integer id) {
        Category category = data.get(id);
        if (category != null) {
            category.setDeleted(true);
            data.put(id, category);
        }
    }
}