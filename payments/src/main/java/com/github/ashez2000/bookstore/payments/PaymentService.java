package com.github.ashez2000.bookstore.payments;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public String createPayment(CreatePaymentDto data) {
        if (!data.pin.equals("2222-2222-2222-2222")) {
            return "Payment Failed";
        }

        var payment = new Payment();
        payment.setOrderId(data.orderId);
        paymentRepository.save(payment);

        return "Payment Success";
    }

}
