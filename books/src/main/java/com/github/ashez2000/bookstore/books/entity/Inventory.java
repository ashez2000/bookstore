package com.github.ashez2000.bookstore.books.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "inventory")
@NoArgsConstructor
@Getter
@Setter
public class Inventory {
    private Long productId;
    private Long stock;

    @Version
    private Long version;
}
