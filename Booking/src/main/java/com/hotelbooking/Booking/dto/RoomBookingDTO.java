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
public class RoomBookingDTO {

    private Date fromDate;
    private Date toDate;
    private String aadhaarNumber;
    private int numOfRooms;
}
