package org.service.inmemory;

import org.model.OrderItem;
import org.rep.OrderItemRep;
import org.service.OrderItemInterface;

import java.util.List;
import java.util.Optional;

public class OrderItemService implements OrderItemInterface {
    private final OrderItemRep orderItemRepo;

    public OrderItemService(OrderItemRep orderItemRepo) {
        this.orderItemRepo = orderItemRepo;
    }

    @Override
    public Optional<OrderItem> findSingle(Integer id) {
        return orderItemRepo.findSingle(id);
    }

    @Override
    public List<OrderItem> findAll() {
        return orderItemRepo.findAll();
    }

    @Override
    public void save(OrderItem orderItem) {
        orderItemRepo.save(orderItem);
    }

    @Override
    public void delete(Integer id) {
        orderItemRepo.delete(id);
    }
}