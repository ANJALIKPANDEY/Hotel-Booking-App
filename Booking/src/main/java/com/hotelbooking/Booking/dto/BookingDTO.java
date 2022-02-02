package com.hotelbooking.Booking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {

    private int bookingId;
    private Date fromDate;
    private Date toDate;
    private String aadhaarNumber;
    private String roomNumbers;
    private double roomPrice;

    //default transaction id is 0
    private int transactionId = 0;

    //default bookedOn is current date
    private Date bookedOn = new Date();

}
