package org.rep.inmemory;

import org.model.OrderItem;
import org.rep.OrderItemRep;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderItemRepo implements OrderItemRep {
    private final Map<Integer, OrderItem> data = new HashMap<>();

    @Override
    public Optional<OrderItem> findSingle(Integer id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<OrderItem> findAll() {
        return data.values().stream()
                .filter(orderItem -> !orderItem.isDeleted())
                .toList();
    }

    @Override
    public void save(OrderItem object) {
        data.put(object.getOrderItemId(), object);
    }

    @Override
    public void delete(Integer id) {
        OrderItem orderItem = data.get(id);
        if (orderItem != null) {
            orderItem.setDeleted(true);
            data.put(id, orderItem);
        }
    }
}