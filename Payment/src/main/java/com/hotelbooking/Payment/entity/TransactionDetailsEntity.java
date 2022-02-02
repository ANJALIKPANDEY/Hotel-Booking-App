package com.hotelbooking.Payment.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@SuppressWarnings("ALL")
@Entity()
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction")
public class TransactionDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transactionId")
    private int id;
    @Column(name = "paymentMode")
    private String paymentMode;
    @Column(name = "bookingId", nullable = false)
    private int bookingId;
    @Column(name = "upiId")
    private String upiId;
    @Column(name = "cardNumber")
    private String cardNumber;



}
