package com.msreservation.controller;

import com.msreservation.dto.request.BookingRequestDto;
import com.msreservation.dto.request.PaymentRequestDto;
import com.msreservation.dto.response.BookingResponseDto;
import com.msreservation.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponseDto> createBooking(@RequestBody BookingRequestDto bookingRequestDto) {
        return new ResponseEntity<>(bookingService.createBooking(bookingRequestDto), HttpStatus.CREATED);
    }

    @PostMapping("/payment/{bookingId}")
    public void addPaymentToBooking(@PathVariable Long bookingId,
                                                      @RequestBody PaymentRequestDto paymentRequestDto) {

        bookingService.addPaymentToBooking(bookingId, paymentRequestDto);
    }

}
