package com.hotelbooking.Payment.controller;

import com.hotelbooking.Payment.dto.TransactionDetailsDTO;
import com.hotelbooking.Payment.services.PaymentService;
import com.hotelbooking.Payment.services.PaymentServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

    @Autowired
    private PaymentServiceImpl paymentServiceImpl;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping(value = "/status")
    public ResponseEntity checkPaymentServiceStatus() {

        return new ResponseEntity("active", HttpStatus.OK);
    }

    @GetMapping(value="/transaction/{id}")
    public ResponseEntity getPayment(@PathVariable(name = "id") int id) {

        return new ResponseEntity(paymentServiceImpl.getTransaction(id), HttpStatus.OK);
    }

    @PostMapping(value = "/transaction", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity processPayment(@RequestBody TransactionDetailsDTO transactionDetailsDTO) {

        return new ResponseEntity(paymentServiceImpl.createTransaction(transactionDetailsDTO), HttpStatus.CREATED);
    }


}
