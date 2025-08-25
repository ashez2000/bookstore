package com.github.ashez2000.bookstore.orders;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrder(Long id) {
        return orderRepository.findById(id);
    }

    public Order createOrder(CreateOrderDto data) {
        var order = new Order();
        order.setProductId(data.productId);
        order.setQuantity(data.quantity);
        order.setTotalAmount(data.totalAmount);
        order.setStatus("CREATED");

        // TODO: Sync call to books service to check inventoryCount

        order = orderRepository.save(order);
        return order;
    }
}
