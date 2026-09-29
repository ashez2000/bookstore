package com.github.ashez2000.bookstore.orders;

import com.github.ashez2000.bookstore.orders.dto.CreatePaymentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("payments")
public interface PaymentFeignClient {


    @GetMapping("/{id}")
    ResponseEntity<String> getBook(@PathVariable("id") Long id);

    @PostMapping("/")
    ResponseEntity<String> pay(@RequestBody CreatePaymentDto body);

}
