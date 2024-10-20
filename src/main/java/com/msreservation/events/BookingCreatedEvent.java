package com.msreservation.events;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingCreatedEvent {
    private Long id;
    private Long userId;
    private BigDecimal totalPrice;
}
