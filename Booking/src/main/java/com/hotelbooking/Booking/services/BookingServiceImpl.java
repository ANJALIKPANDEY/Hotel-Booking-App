package com.hotelbooking.Booking.services;

import com.hotelbooking.Booking.dto.BookingDTO;
import com.hotelbooking.Booking.dto.PaymentDTO;
import com.hotelbooking.Booking.dto.RoomBookingDTO;
import com.hotelbooking.Booking.entity.BookingInfoEntity;
import com.hotelbooking.Booking.exception.RecordNotFoundException;
import com.hotelbooking.Booking.feign.PaymentServiceClient;
import com.hotelbooking.Booking.repository.BookingRepository;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.modelmapper.ModelMapper;
import org.joda.time.ReadableInstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.Random;


@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PaymentServiceClient paymentServiceClient;

    @Override
    public BookingDTO createBooking(RoomBookingDTO bookingRequest) throws RuntimeException {
        StringBuilder roomNumbers = new StringBuilder("");
        double roomPrice;

        int totalRoomsRequested = bookingRequest.getNumOfRooms();

        ReadableInstant fromDateJoda = new DateTime(bookingRequest.getFromDate());
        ReadableInstant toDateJoda = new DateTime(bookingRequest.getToDate());
        int totalStay = Days.daysBetween(fromDateJoda, toDateJoda).getDays() + 1;

        //limiting conditions
        if (totalRoomsRequested <= 0 || totalRoomsRequested > 100)
            throw new RuntimeException("Incorrect Request for Rooms");
        if (totalStay <= 0) throw new RuntimeException("From Date couldn't be less than To Date");

        ArrayList<String> reservedRooms = getRandomNumbers(bookingRequest.getNumOfRooms());
        for (String room : reservedRooms) roomNumbers.append(room).append(",");
        roomNumbers = new StringBuilder(StringUtils.strip(roomNumbers.toString(), ","));

        roomPrice = (double) (1000 * totalRoomsRequested * totalStay);

        BookingDTO bookingDTO = BookingDTO.builder()
                .fromDate(bookingRequest.getFromDate())
                .toDate(bookingRequest.getToDate())
                .aadhaarNumber(bookingRequest.getAadhaarNumber())
                .roomNumbers(roomNumbers.toString())
                .roomPrice(roomPrice)
                .bookedOn(new Date())
                .build();

        BookingInfoEntity bookingInfoEntity = modelMapper.map(bookingDTO, BookingInfoEntity.class);
        BookingInfoEntity savedBookingInfoEntity = bookingRepository.save(bookingInfoEntity);
        return modelMapper.map(savedBookingInfoEntity, BookingDTO.class);
    }

    @Override
    public BookingDTO getBooking(int bookingId) {

        Optional<BookingInfoEntity> bookingInfo = Optional.ofNullable(bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RecordNotFoundException("Invalid Booking Id")));

        if (bookingInfo == null) throw new RuntimeException("Unable to locate the booking Id : " + bookingId);
        BookingDTO bookingDTO = modelMapper.map(bookingInfo.get(), BookingDTO.class);
        return bookingDTO;

    }

    public BookingDTO processPayment(int bookingId, PaymentDTO paymentDTO) throws IllegalArgumentException {

        BookingDTO bookingDTO = this.getBooking(bookingId);
        BookingInfoEntity bookingInfo = modelMapper.map(bookingDTO, BookingInfoEntity.class);
        PaymentDTO savedTransaction = paymentServiceClient.processPayment(paymentDTO);
        //update transaction id on the booking
        bookingInfo.setTransactionId(savedTransaction.getId());
//        bookingDTO.setTransactionId(savedTransaction);
        BookingInfoEntity savedBookingInfoEntity = bookingRepository.save(bookingInfo);
        String message = "Booking confirmed for user with aadhaar number: "
                + bookingInfo.getAadhaarNumber()
                +    "    |    "
                + "Here are the booking details:    " + bookingInfo.toString();
        System.out.println(message);
        return modelMapper.map(savedBookingInfoEntity, BookingDTO.class);
    }

    private ArrayList<String> getRandomNumbers(int count) {
        Random rand = new Random();
        int upperBound = 100;
        ArrayList<String> numberList = new ArrayList<>();

        for (int i = 0; i < count; i++) numberList.add(String.valueOf(rand.nextInt(upperBound)));
        return numberList;
    }
}
