package org.rep.jdbc;

import org.model.Product;
import org.rep.ProductRep;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JdbcProductRep extends JdbcBaseRep<Product> implements ProductRep {

    private Product mapProduct(ResultSet rs) throws SQLException {
        Product product = new Product(
                rs.getInt("product_id"),
                rs.getInt("seller_id"),
                rs.getInt("category_id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getFloat("price_per_unit"),
                rs.getString("unit"),
                rs.getFloat("quantity"),
                rs.getTimestamp("production_date")
        );
        product.setLastUpdated(rs.getTimestamp("last_updated"));
        product.setDeleted(rs.getBoolean("is_deleted"));
        return product;
    }

    @Override
    public Optional<Product> findSingle(Integer id) {
        String sql = "SELECT * FROM products WHERE product_id = ? AND is_deleted = false";
        List<Product> products = executeQuery(sql, this::mapProduct, id);
        return products.stream().findFirst();
    }

    @Override
    public List<Product> findAll() {
        String sql = "SELECT * FROM products WHERE is_deleted = false";
        return executeQuery(sql, this::mapProduct);
    }

    @Override
    public void save(Product product) {
        if (product.getProductId() == null) {
            String sql = "INSERT INTO products (seller_id, category_id, name, description, price_per_unit, unit, quantity, last_updated, production_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            Integer generatedId = executeInsert(sql,
                    product.getSellerId(),
                    product.getCategoryId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPricePerUnit(),
                    product.getUnit(),
                    product.getQuantity(),
                    product.getLastUpdated(),
                    product.getProductionDate()
            );
            product.setProductId(generatedId);
        } else {
            String sql = "UPDATE products SET seller_id = ?, category_id = ?, name = ?, description = ?, price_per_unit = ?, unit = ?, quantity = ?, last_updated = ?, production_date = ?, is_deleted = ? WHERE product_id = ?";
            try {
                executeUpdate(sql,
                        product.getSellerId(),
                        product.getCategoryId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPricePerUnit(),
                        product.getUnit(),
                        product.getQuantity(),
                        product.getLastUpdated(),
                        product.getProductionDate(),
                        product.isDeleted(),
                        product.getProductId()
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "UPDATE products SET is_deleted = true WHERE product_id = ?";
        try {
            executeUpdate(sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findBySellerId(Integer sellerId) {
        String sql = "SELECT * FROM products WHERE seller_id = ? AND is_deleted = false";
        return executeQuery(sql, this::mapProduct, sellerId);
    }

    @Override
    public List<Product> findByCategoryId(Integer categoryId) {
        String sql = "SELECT * FROM products WHERE category_id = ? AND is_deleted = false";
        return executeQuery(sql, this::mapProduct, categoryId);
    }
}