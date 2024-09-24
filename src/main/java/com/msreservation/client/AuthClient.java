package com.msreservation.client;

import com.msreservation.dto.response.AuthResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-auth", url = "http://localhost:9092/api/v1/user/")
public interface AuthClient {

    @GetMapping("{id}")
    ResponseEntity<AuthResponseDto> getUserById(@PathVariable Long id);
}
