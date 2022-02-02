package com.hotelbooking.Booking.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@SuppressWarnings("ALL")
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "booking")
public class BookingInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bookingId")
    private int bookingId;

    @Column(name = "fromDate")
    private Date fromDate;

    @Column(name = "toDate")
    private Date toDate;

    @Column(name = "aadhaarNumber")
    private String aadhaarNumber;

    @Column(name = "numOfRooms")
    private String roomNumbers;

    @Column(name = "roomPrice", nullable = false)
    private double roomPrice;

    @Column(name = "transactionId", columnDefinition = "integer default 0")
    private int transactionId = 0;

    @Column(name = "bookedOn")
    private Date bookedOn;


}
