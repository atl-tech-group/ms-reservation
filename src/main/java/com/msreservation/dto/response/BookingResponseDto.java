package com.msreservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDto {

    private Long id;
    private Long userId;
    private Long propertyId;

    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private Integer numGuests;
    private BigDecimal nightlyPrice;
    private BigDecimal totalPrice;
}
