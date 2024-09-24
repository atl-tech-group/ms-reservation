package com.msreservation.dto.request;

import com.msreservation.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentRequestDto {

    private Long bookingId;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
}
