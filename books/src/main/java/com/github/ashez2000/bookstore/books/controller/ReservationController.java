package com.github.ashez2000.bookstore.books.controller;

import com.github.ashez2000.bookstore.books.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/reservations", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/{orderId}/release")
    public ResponseEntity<Void> release(@PathVariable Long orderId) {
        reservationService.releaseBook(orderId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{orderId}/confirm")
    public ResponseEntity<Void> confirm(@PathVariable Long orderId) {
        reservationService.confirmReservation(orderId);
        return ResponseEntity.ok().build();
    }

}
