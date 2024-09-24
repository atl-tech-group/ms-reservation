package com.msreservation.dto.request;

import com.msreservation.entity.Payment;
import com.msreservation.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class BookingRequestDto {
    private Long userId;
    private Long propertyId;

    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private Integer numGuests;
    private BigDecimal nightlyPrice;
}
