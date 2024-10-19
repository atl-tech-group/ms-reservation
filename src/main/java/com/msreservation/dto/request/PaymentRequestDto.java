package com.msreservation.dto.request;

import com.msreservation.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class PaymentRequestDto {

    private Long bookingId;
    private BigDecimal amount;
    private Set<PaymentMethod> paymentMethod;
}
