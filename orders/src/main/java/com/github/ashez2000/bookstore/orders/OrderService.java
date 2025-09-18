package com.github.ashez2000.bookstore.orders;

import com.github.ashez2000.bookstore.orders.dto.CreateOrderDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private BookFeignClient bookFeignClient;

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrder(Long id) {
        return orderRepository.findById(id);
    }

    public Order createOrder(CreateOrderDto dto) {
        var product = bookFeignClient.getBook(dto.productId);
        System.out.println("PRODUCT: " + product.getBody());
        if (product == null) {
            return null;
        }

        var order = new Order();
        order.setProductId(dto.productId);
        order.setQuantity(dto.quantity);
        order.setTotalAmount(dto.totalAmount);
        order.setStatus("CREATED");

        return orderRepository.save(order);
    }
}
