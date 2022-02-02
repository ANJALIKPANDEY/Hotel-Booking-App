package com.hotelbooking.Booking.repository;

import com.hotelbooking.Booking.entity.BookingInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingInfoEntity, Integer> {

}
