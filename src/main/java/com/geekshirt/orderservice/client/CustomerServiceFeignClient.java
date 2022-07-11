package com.geekshirt.orderservice.client;

import com.geekshirt.orderservice.dto.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("CUSTOMERSERVICE")
public interface CustomerServiceFeignClient {

   @GetMapping(value = "/api/v1/account/{id}")
    public AccountDto getAccountById(@PathVariable("id") String id);
}
