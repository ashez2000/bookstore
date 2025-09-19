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
        payment.setOrderId(data.orderId);
        payment.setPaymentMethod(data.paymentMethod);

        if (data.pin.equals("2222-2222-2222-2222")) {
            payment.setStatus("Success");
        } else {
            payment.setStatus("Failure");
        }

        payment = paymentRepository.save(payment);
        return payment;
    }
}
