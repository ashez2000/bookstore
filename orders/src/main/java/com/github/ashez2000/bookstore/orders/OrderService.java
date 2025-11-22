package com.github.ashez2000.bookstore.orders;

import com.github.ashez2000.bookstore.orders.dto.CreateOrderDto;
import com.github.ashez2000.bookstore.orders.dto.QuantityDto;
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
        bookFeignClient.reserve(dto.getBookId(), new QuantityDto(dto.getQuantity()));

        var order = new Order();
        order.setBookId(dto.getBookId());
        order.setQuantity(dto.getQuantity());
        order.setTotalAmount(dto.getTotalAmount());
        order.setStatus("CREATED");

        return orderRepository.save(order);
    }
}
