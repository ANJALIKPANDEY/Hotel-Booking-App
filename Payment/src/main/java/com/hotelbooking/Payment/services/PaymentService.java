package com.hotelbooking.Payment.services;


import com.hotelbooking.Payment.dto.TransactionDetailsDTO;

public interface PaymentService {
    public TransactionDetailsDTO createTransaction(TransactionDetailsDTO transactionDetailsDTO);

    public TransactionDetailsDTO getTransaction(int transactionId);
}
