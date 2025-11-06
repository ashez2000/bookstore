package com.github.ashez2000.bookstore.books.repository;

import com.github.ashez2000.bookstore.books.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
