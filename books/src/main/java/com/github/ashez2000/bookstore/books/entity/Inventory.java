package com.github.ashez2000.bookstore.books.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "inventory")
@NoArgsConstructor
@Getter
@Setter
public class Inventory {
    @Column(nullable = false)
    @Id
    private Long productId;

    @Column(nullable = false)
    private Integer stock;

    @Version
    private Long version;
}
