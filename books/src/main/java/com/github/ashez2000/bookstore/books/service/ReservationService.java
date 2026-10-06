package com.github.ashez2000.bookstore.books.service;

import com.github.ashez2000.bookstore.books.entity.Inventory;
import com.github.ashez2000.bookstore.books.entity.Reservation;
import com.github.ashez2000.bookstore.books.entity.ReservationStatus;
import com.github.ashez2000.bookstore.books.repository.InventoryRepository;
import com.github.ashez2000.bookstore.books.repository.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional
    public void reserveBook(Long orderId, Long bookId) {
        // 1. Idempotency Check: Already processed?
        Optional<Reservation> existing = reservationRepository.findByOrderId(orderId);
        if (existing.isPresent()) {
            if (existing.get().getStatus() == ReservationStatus.RESERVED) {
                return; // Duplicate Feign call: safe no-op
            }
            throw new IllegalStateException("Reservation in invalid state: " + existing.get().getStatus());
        }

        // 2. Lock book record & check stock
        Inventory inventory = inventoryRepository.findByBookIdForUpdate(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found: " + bookId));

        inventory.decrementStock();
        inventoryRepository.save(inventory);

        // 3. Record reservation
        Reservation reservation = new Reservation(orderId, bookId);
        reservationRepository.save(reservation);
    }

    @Transactional
    public void releaseBook(Long orderId) {
        // 1. Check if reservation exists
        Optional<Reservation> optionalReservation = reservationRepository.findByOrderId(orderId);
        if (optionalReservation.isEmpty()) {
            return; // Order was never reserved or failed earlier: safe no-op
        }

        Reservation reservation = optionalReservation.get();
        if (reservation.getStatus() == ReservationStatus.RELEASED) {
            return; // Already compensated: safe no-op
        }

        // 2. Lock book and replenish inventory
        Inventory inventory = inventoryRepository.findByBookIdForUpdate(reservation.getBookId())
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        inventory.incrementStock();
        inventoryRepository.save(inventory);

        // 3. Mark reservation as released
        reservation.release();
        reservationRepository.save(reservation);
    }

    @Transactional
    public void confirmReservation(Long orderId) {
        reservationRepository.findByOrderId(orderId).ifPresent(reservation -> {
            reservation.confirm();
            reservationRepository.save(reservation);
        });
    }

}
