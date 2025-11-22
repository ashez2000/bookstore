package com.github.ashez2000.bookstore.orders;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookId;
    private Integer quantity;
    private Integer totalAmount;
    private String status;
}
