package org.rep.jdbc;

import org.model.Seller;
import org.rep.SellerRep;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JdbcSellerRep extends JdbcBaseRep<Seller> implements SellerRep {

    private Seller mapSeller(ResultSet rs) throws SQLException {
        Seller seller = new Seller(
                rs.getInt("seller_id"),
                rs.getInt("user_id"),
                rs.getString("farm_name"),
                rs.getString("description")
        );
        seller.setRating(rs.getObject("rating") != null ? rs.getInt("rating") : null);
        seller.setDeleted(rs.getBoolean("is_deleted"));
        return seller;
    }

    @Override
    public Optional<Seller> findSingle(Integer id) {
        String sql = "SELECT * FROM sellers WHERE seller_id = ? AND is_deleted = false";
        List<Seller> sellers = executeQuery(sql, this::mapSeller, id);
        return sellers.stream().findFirst();
    }

    @Override
    public List<Seller> findAll() {
        String sql = "SELECT * FROM sellers WHERE is_deleted = false";
        return executeQuery(sql, this::mapSeller);
    }

    @Override
    public void save(Seller seller) {
        if (seller.getSellerId() == null) {
            String sql = "INSERT INTO sellers (user_id, farm_name, description, rating) VALUES (?, ?, ?, ?)";
            Integer generatedId = executeInsert(sql,
                    seller.getUserId(),
                    seller.getFarmName(),
                    seller.getDescription(),
                    seller.getRating()
            );
            if (generatedId != null) {
                seller.setSellerId(generatedId);
            }
        } else {
            String sql = "UPDATE sellers SET user_id = ?, farm_name = ?, description = ?, rating = ?, is_deleted = ? WHERE seller_id = ?";
            executeUpdate(sql,
                    seller.getUserId(),
                    seller.getFarmName(),
                    seller.getDescription(),
                    seller.getRating(),
                    seller.isDeleted(),
                    seller.getSellerId()
            );
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "UPDATE sellers SET is_deleted = true WHERE seller_id = ?";
        executeUpdate(sql, id);
    }

    @Override
    public Optional<Seller> findByUserId(Integer userId) {
        String sql = "SELECT * FROM sellers WHERE user_id = ? AND is_deleted = false";
        List<Seller> sellers = executeQuery(sql, this::mapSeller, userId);
        return sellers.stream().findFirst();
    }
}