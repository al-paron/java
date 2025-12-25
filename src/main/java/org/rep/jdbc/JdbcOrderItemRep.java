package org.rep.jdbc;

import org.model.OrderItem;
import org.rep.OrderItemRep;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JdbcOrderItemRep extends JdbcBaseRep<OrderItem> implements OrderItemRep {

    private OrderItem mapOrderItem(ResultSet rs) throws SQLException {
        OrderItem orderItem = new OrderItem(
                rs.getInt("order_item_id"),
                rs.getInt("order_id"),
                rs.getInt("product_id"),
                rs.getFloat("quantity"),
                rs.getFloat("price_at_time")
        );
        orderItem.setDeleted(rs.getBoolean("is_deleted"));
        return orderItem;
    }

    @Override
    public Optional<OrderItem> findSingle(Integer id) {
        String sql = "SELECT * FROM order_items WHERE order_item_id = ? AND is_deleted = false";
        List<OrderItem> orderItems = executeQuery(sql, this::mapOrderItem, id);
        return orderItems.stream().findFirst();
    }

    @Override
    public List<OrderItem> findAll() {
        String sql = "SELECT * FROM order_items WHERE is_deleted = false";
        return executeQuery(sql, this::mapOrderItem);
    }

    @Override
    public void save(OrderItem orderItem) {
        if (orderItem.getOrderItemId() == null) {
            String sql = "INSERT INTO order_items (order_id, product_id, quantity, price_at_time) VALUES (?, ?, ?, ?)";
            Integer generatedId = executeInsert(sql,
                    orderItem.getOrderId(),
                    orderItem.getProductId(),
                    orderItem.getQuantity(),
                    orderItem.getPriceAtTime()
            );
            if (generatedId != null) {
                orderItem.setOrderItemId(generatedId);
            }
        } else {
            String sql = "UPDATE order_items SET order_id = ?, product_id = ?, quantity = ?, price_at_time = ?, is_deleted = ? WHERE order_item_id = ?";
            executeUpdate(sql,
                    orderItem.getOrderId(),
                    orderItem.getProductId(),
                    orderItem.getQuantity(),
                    orderItem.getPriceAtTime(),
                    orderItem.isDeleted(),
                    orderItem.getOrderItemId()
            );
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "UPDATE order_items SET is_deleted = true WHERE order_item_id = ?";
        executeUpdate(sql, id);
    }
}