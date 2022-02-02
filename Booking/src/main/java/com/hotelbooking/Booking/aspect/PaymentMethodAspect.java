package com.hotelbooking.Booking.aspect;

import com.hotelbooking.Booking.dto.PaymentDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PaymentMethodAspect {

    @Before(value = "execution(* com.hotelbooking.Booking.services.BookingServiceImpl.processPayment(..)) && args(bookingId, paymentDTO)")
    public void beforeAdvice(JoinPoint joinPoint, int bookingId, PaymentDTO paymentDTO){
        //check transaction type
        if (!((paymentDTO.getPaymentMode().equals("CARD")||
                paymentDTO.getPaymentMode().equals("UPI"))) {
            throw new IllegalArgumentException("Invalid Mode Of Payment");
        }
    }

}
