package org.service.inmemory;

import org.model.Category;
import org.rep.inmemory.CategoryRepo;
import org.service.CategoryInterface;

import java.util.List;
import java.util.Optional;

public class CategoryService implements CategoryInterface {
    private final CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Optional<Category> findSingle(Integer id) {
        return categoryRepo.findSingle(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public void save(Category category) {
        categoryRepo.save(category);
    }

    @Override
    public void delete(Integer id) {
        categoryRepo.delete(id);
    }
}