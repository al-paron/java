package org.rep.inmemory;

import org.model.Order;
import org.rep.OrderRep;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderRepo implements OrderRep {
    private final Map<Integer, Order> data = new HashMap<>();

    @Override
    public Optional<Order> findSingle(Integer id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<Order> findAll() {
        return data.values().stream()
                .filter(order -> !order.isDeleted())
                .toList();
    }

    @Override
    public void save(Order object) {
        data.put(object.getOrderId(), object);
    }

    @Override
    public void delete(Integer id) {
        Order order = data.get(id);
        if (order != null) {
            order.setDeleted(true);
            data.put(id, order);
        }
    }
}