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

    @Id
    private Long bookId;

    @Column(nullable = false)
    private Integer stock;

    public void decrementStock() {
        if (this.stock <= 0) {
            throw new IllegalStateException("Book is out of stock");
        }
        this.stock--;
    }

    public void incrementStock() {
        this.stock++;
    }

}
