package com.github.ashez2000.bookstore.payments;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PaymentController {
    private PaymentService paymentService;

    @PostMapping
    ResponseEntity<Payment> createPayment(@RequestBody CreatePaymentDto data) {
        var payment = paymentService.createPayment(data);
        if (payment.getStatus().equals("Success")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(payment);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(payment);
    }
}
