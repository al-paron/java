package org.rep.jdbc;

import org.model.Category;
import org.rep.CategoryRep;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JdbcCategoryRep extends JdbcBaseRep<Category> implements CategoryRep {

    private Category mapCategory(ResultSet rs) throws SQLException {
        Category category = new Category(
                rs.getInt("category_id"),
                rs.getString("name"),
                rs.getString("description")
        );
        category.setDeleted(rs.getBoolean("is_deleted"));
        return category;
    }

    @Override
    public Optional<Category> findSingle(Integer id) {
        String sql = "SELECT * FROM categories WHERE category_id = ? AND is_deleted = false";
        List<Category> categories = executeQuery(sql, this::mapCategory, id);
        return categories.stream().findFirst();
    }

    @Override
    public List<Category> findAll() {
        String sql = "SELECT * FROM categories WHERE is_deleted = false";
        return executeQuery(sql, this::mapCategory);
    }

    @Override
    public void save(Category category) {
        if (category.getCategoryId() == null) {
            String sql = "INSERT INTO categories (name, description) VALUES (?, ?)";
            Integer generatedId = executeInsert(sql, category.getName(), category.getDescription());
            if (generatedId != null) {
                category.setCategoryId(generatedId);
            }
        } else {
            String sql = "UPDATE categories SET name = ?, description = ?, is_deleted = ? WHERE category_id = ?";
            executeUpdate(sql,
                    category.getName(),
                    category.getDescription(),
                    category.isDeleted(),
                    category.getCategoryId()
            );
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "UPDATE categories SET is_deleted = true WHERE category_id = ?";
        executeUpdate(sql, id);
    }
}