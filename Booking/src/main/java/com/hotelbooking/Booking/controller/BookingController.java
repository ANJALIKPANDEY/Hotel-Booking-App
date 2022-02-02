package com.hotelbooking.Booking.controller;

import com.hotelbooking.Booking.dto.PaymentDTO;
import com.hotelbooking.Booking.dto.RoomBookingDTO;
import com.hotelbooking.Booking.services.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/hotel")
public class BookingController {

    @Autowired
    private BookingServiceImpl bookingServiceImpl;

    /**
     * 127.0.0.1:8080/hotel/booking
     */

    @GetMapping(value = "/status")
    public ResponseEntity checkPaymentServiceStatus() {

        return new ResponseEntity("active", HttpStatus.OK);
    }

    @GetMapping(value = "/booking/{bookingId}")
    public ResponseEntity getBooking(@PathVariable(name = "bookingId") int bookingId) {

        return new ResponseEntity(bookingServiceImpl.getBooking(bookingId), HttpStatus.OK);
    }

    @PostMapping(value = "/booking", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createRoomBooking(@RequestBody RoomBookingDTO roomBookingDTO) {

        return new ResponseEntity(bookingServiceImpl.createBooking(roomBookingDTO), HttpStatus.CREATED);
    }

    @PostMapping(value = "/booking/{bookingId}/transaction", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity processPayment(@PathVariable(name = "bookingId") int bookingId,
            @RequestBody PaymentDTO transaction) {

        return new ResponseEntity(bookingServiceImpl.processPayment(bookingId, transaction),HttpStatus.CREATED);
    }


}
