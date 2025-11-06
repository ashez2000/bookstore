package com.github.ashez2000.bookstore.books.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
}
