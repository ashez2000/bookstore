package com.github.ashez2000.bookstore.payments;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class PaymentController {

    private PaymentService paymentService;

    @PostMapping
    ResponseEntity<String> createPayment(@RequestBody CreatePaymentDto data) {
        var status = paymentService.createPayment(data);
        if (status.equals("Payment Failed")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(status);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(status);
    }

    @GetMapping("/{orderId}")
    ResponseEntity<String> getPayment(@PathVariable long orderId) {
        var status = paymentService.getStatus(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

}
