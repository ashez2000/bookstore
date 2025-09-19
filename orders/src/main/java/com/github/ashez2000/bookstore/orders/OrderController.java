package com.github.ashez2000.bookstore.orders;

import com.github.ashez2000.bookstore.orders.dto.CreateOrderDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/orders", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    @GetMapping
    ResponseEntity<List<Order>> getOrders() {
        var orders = orderService.getOrders();
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @GetMapping("{id}")
    ResponseEntity<Optional<Order>> getOrders(@PathVariable("id") Long id) {
        var order = orderService.getOrder(id);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @PostMapping
    ResponseEntity<Order> createOrder(@RequestBody CreateOrderDto data) {
        // TODO: Check books stock
        // TODO: Reserve books

        // Create Order
        var order = orderService.createOrder(data);

        // TODO: Send OrderCreatedEvent

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}
