package com.hotelbooking.Booking.feign;


import com.hotelbooking.Booking.dto.PaymentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "PAYMENT-SERVICE", url = "http://localhost:8083/payment")
public interface PaymentServiceClient {

    @GetMapping(value = "/transaction/{id}")
    public PaymentDTO checkPaymentServiceStatus(@PathVariable(name = "id") int id);

    @PostMapping(value = "/transaction", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PaymentDTO processPayment(@RequestBody PaymentDTO paymentDTO);

}
