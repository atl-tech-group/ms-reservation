package com.msreservation.entity;

import com.msreservation.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate paymentDate;
    private BigDecimal amount;

    @Enumerated(value = EnumType.STRING)
    private PaymentMethod paymentMethod;

    @OneToOne(mappedBy = "payment")
    private Booking booking;
}
