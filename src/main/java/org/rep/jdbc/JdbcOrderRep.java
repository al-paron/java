package org.rep.jdbc;

import org.model.Order;
import org.rep.OrderRep;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JdbcOrderRep extends JdbcBaseRep<Order> implements OrderRep {

    private Order mapOrder(ResultSet rs) throws SQLException {
        Order order = new Order(
                rs.getInt("order_id"),
                rs.getInt("user_id"),
                rs.getString("address_line"),
                rs.getString("status")
        );
        order.setCreatedAt(rs.getTimestamp("created_at"));
        order.setDeleted(rs.getBoolean("is_deleted"));
        return order;
    }

    @Override
    public Optional<Order> findSingle(Integer id) {
        String sql = "SELECT * FROM orders WHERE order_id = ? AND is_deleted = false";
        List<Order> orders = executeQuery(sql, this::mapOrder, id);
        return orders.stream().findFirst();
    }

    @Override
    public List<Order> findAll() {
        String sql = "SELECT * FROM orders WHERE is_deleted = false";
        return executeQuery(sql, this::mapOrder);
    }

    @Override
    public void save(Order order) {
        if (order.getOrderId() == null) {
            String sql = "INSERT INTO orders (user_id, address_line, status, created_at) VALUES (?, ?, ?, ?)";
            Integer generatedId = executeInsert(sql,
                    order.getCustomerId(),
                    order.getAddressLine(),
                    order.getStatus(),
                    order.getCreatedAt()
            );
            if (generatedId != null) {
                order.setOrderId(generatedId);
            }
        } else {
            String sql = "UPDATE orders SET user_id = ?, address_line = ?, status = ?, created_at = ?, is_deleted = ? WHERE order_id = ?";
            executeUpdate(sql,
                    order.getCustomerId(),
                    order.getAddressLine(),
                    order.getStatus(),
                    order.getCreatedAt(),
                    order.isDeleted(),
                    order.getOrderId()
            );
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "UPDATE orders SET is_deleted = true WHERE order_id = ?";
        executeUpdate(sql, id);
    }
}