package com.msreservation.client;

import com.msreservation.dto.response.PropertyResponseFein;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "ms-property", url = "http://localhost:9093/api/v1/property/")
public interface PropertyClient {
    @GetMapping("/fein/{id}")
    PropertyResponseFein getForFeinPropertyById(@PathVariable Long id);

}
