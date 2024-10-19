package com.msreservation.client;

import com.msreservation.dto.request.PaymentRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-payment", url = "http://localhost:9097/api/v1/payment/")
public interface PaymentClient {

    @PostMapping
    ResponseEntity<Void> createPayment(@RequestBody PaymentRequestDto paymentRequestDto);
}
