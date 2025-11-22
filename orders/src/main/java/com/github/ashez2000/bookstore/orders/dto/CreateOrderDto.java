package com.github.ashez2000.bookstore.orders.dto;

import lombok.Data;

@Data
public class CreateOrderDto {
    private Long bookId;
    private Integer quantity;
    private Integer totalAmount;
}
