package com.geekshirt.orderservice.client;

import com.geekshirt.orderservice.config.OrderServiceConfig;
import com.geekshirt.orderservice.dto.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerServiceDiscoveryClient {
    private RestTemplate restTemplate;

    public CustomerServiceDiscoveryClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Autowired
    private DiscoveryClient discoveryClient;

    public Optional<AccountDto> findAccount(String accountId) {
        Optional<AccountDto> result = Optional.empty();
        try {
            List<ServiceInstance> serviceInstances = discoveryClient.getInstances("CUSTOMERSERVICE");

            if (serviceInstances.isEmpty()) {
                return result;
            }

            ServiceInstance serviceInstance = serviceInstances.get(0);

            String url = String.format("%s/api/v1/account/%s", serviceInstance.getUri().toString(), accountId);

            result = Optional.ofNullable(restTemplate.getForObject(url, AccountDto.class, accountId));
        }
        catch (HttpClientErrorException ex)   {
            if (ex.getStatusCode() != HttpStatus.NOT_FOUND) {
                throw ex;
            }
        }
        return result;
    }
}
