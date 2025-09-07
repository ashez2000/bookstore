package com.github.ashez2000.bookstore.payments;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentService {
    private PaymentRepository paymentRepository;

    public Payment createPayment(CreatePaymentDto data) {
        var payment = new Payment();
        payment.setOrderId(data.orderId);
        payment.setPaymentMethod(data.paymentMethod);
        payment.setStatus(data.status);

        payment = paymentRepository.save(payment);
        return payment;
    }
}
