package com.hotelbooking.Payment.services;

import com.hotelbooking.Payment.dto.TransactionDetailsDTO;
import com.hotelbooking.Payment.entity.TransactionDetailsEntity;
import com.hotelbooking.Payment.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TransactionDetailsDTO createTransaction(TransactionDetailsDTO transactionDetailsDTO) {

        TransactionDetailsEntity transactionDetails = modelMapper.map(transactionDetailsDTO, TransactionDetailsEntity.class);
        transactionDetails = paymentRepository.save(transactionDetails);
        TransactionDetailsDTO transactionDetailsDTOResponse = modelMapper.map(transactionDetails, TransactionDetailsDTO.class);
        System.out.println(transactionDetailsDTOResponse.getId());
        return transactionDetailsDTOResponse;
    }

    @Override
    public TransactionDetailsDTO getTransaction(int transactionId) {

        TransactionDetailsEntity transactionDetailsEntity = paymentRepository.findById(transactionId)
                .orElseThrow(()->new RuntimeException("Unable to find the data you are looking for id" + transactionId));

        return modelMapper.map(transactionDetailsEntity, TransactionDetailsDTO.class);
    }
}
