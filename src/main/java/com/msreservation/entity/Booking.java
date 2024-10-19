package com.msreservation.entity;

import com.msreservation.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Booking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long propertyId;

    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private Integer numGuests;
    private BigDecimal nightlyPrice;
    private BigDecimal totalPrice;

    @Enumerated(value = EnumType.STRING)
    private BookingStatus bookingStatus;
}
