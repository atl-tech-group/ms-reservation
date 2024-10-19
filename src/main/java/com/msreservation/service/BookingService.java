package com.msreservation.service;

import com.msreservation.client.AuthClient;
import com.msreservation.client.PaymentClient;
import com.msreservation.client.PropertyClient;
import com.msreservation.dto.request.BookingRequestDto;
import com.msreservation.dto.request.PaymentRequestDto;
import com.msreservation.dto.response.AuthResponseDto;
import com.msreservation.dto.response.BookingResponseDto;
import com.msreservation.dto.response.PropertyResponseFein;
import com.msreservation.entity.Booking;
import com.msreservation.enums.BookingStatus;
import com.msreservation.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final PaymentClient paymentClient;
    private final AuthClient authClient;
    private final PropertyClient propertyClient;

    public BookingResponseDto createBooking(BookingRequestDto bookingRequestDto) {

        AuthResponseDto authResponseDto = authClient.getUserById(bookingRequestDto.getUserId()).getBody();
        PropertyResponseFein propertyResponse = propertyClient.getForFeinPropertyById(bookingRequestDto.getPropertyId());

        Booking booking = new Booking();

        if (authResponseDto != null && propertyResponse != null) {
            booking.setUserId(authResponseDto.getId());
            booking.setPropertyId(propertyResponse.getId());
        }
        booking.setPropertyId(booking.getPropertyId());
        booking.setCheckinDate(bookingRequestDto.getCheckinDate());
        booking.setCheckoutDate(bookingRequestDto.getCheckoutDate());
        booking.setNumGuests(bookingRequestDto.getNumGuests());
        booking.setNightlyPrice(bookingRequestDto.getNightlyPrice());

        booking.setTotalPrice(calculateTotalPrice(bookingRequestDto.getNightlyPrice(),
                                                  bookingRequestDto.getCheckinDate(),
                                                  bookingRequestDto.getCheckoutDate()));

        bookingRepository.save(booking);

        return new BookingResponseDto(
                booking.getId(),
                booking.getUserId(),
                booking.getPropertyId(),
                booking.getCheckinDate(),
                booking.getCheckoutDate(),
                booking.getNumGuests(),
                booking.getNightlyPrice(),
                booking.getTotalPrice()
        );

    }

    public void addPaymentToBooking(Long bookingId, PaymentRequestDto paymentRequestDto) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new RuntimeException("Reservation not ById " + bookingId));
        paymentClient.createPayment(paymentRequestDto);
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        bookingRepository.save(booking);
    }

    private BigDecimal calculateTotalPrice(BigDecimal nightlyPrice, LocalDate checkInDate, LocalDate checkinOutDate) {
        long duration = Stream.iterate(checkInDate, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(checkInDate, checkinOutDate))
                .count();
        return nightlyPrice.multiply(BigDecimal.valueOf(duration));
    }
}
