package org.service.inmemory;

import org.model.Order;
import org.rep.inmemory.OrderRepo;
import org.service.OrderInterface;

import java.util.List;
import java.util.Optional;

public class OrderService implements OrderInterface {
    private final OrderRepo orderRepo;

    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public Optional<Order> findSingle(Integer id) {
        return orderRepo.findSingle(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepo.findAll();
    }

    @Override
    public void save(Order order) {
        orderRepo.save(order);
    }

    @Override
    public void delete(Integer id) {
        orderRepo.delete(id);
    }
}