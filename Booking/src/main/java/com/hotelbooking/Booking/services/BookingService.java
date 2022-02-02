package com.hotelbooking.Booking.services;


import com.hotelbooking.Booking.dto.BookingDTO;
import com.hotelbooking.Booking.dto.RoomBookingDTO;

public interface BookingService {

    public BookingDTO createBooking(RoomBookingDTO bookingRequest);

    public BookingDTO getBooking(int bookingId);

}
