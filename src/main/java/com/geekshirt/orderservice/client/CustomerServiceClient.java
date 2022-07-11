package com.geekshirt.orderservice.client;

import com.geekshirt.orderservice.config.OrderServiceConfig;
import com.geekshirt.orderservice.dto.AccountDto;
import com.geekshirt.orderservice.dto.AddressDto;
import com.geekshirt.orderservice.dto.CreditCardDto;
import com.geekshirt.orderservice.dto.CustomerDto;
import com.geekshirt.orderservice.util.AccountStatus;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Component
public class CustomerServiceClient {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderServiceConfig config;

    @HystrixCommand(fallbackMethod = "fallbackFindAccount",
            threadPoolKey = "customerThreadPool",
            threadPoolProperties = {@HystrixProperty(name="coreSize", value="10"),
                    @HystrixProperty(name="maxQueueSize", value="5")},
            commandProperties = {
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="10"),
            @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds", value="40000"),
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="10000"),
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="13000")
    })
    public Optional<AccountDto> findAccount(String accountId) {
        Optional<AccountDto> result = Optional.empty();
        try {
            result = Optional.ofNullable(restTemplate.getForObject(config.getCustomerServiceUrl() + "/{id}", AccountDto.class, accountId));
        }
        catch (HttpClientErrorException ex)   {
            if (ex.getStatusCode() != HttpStatus.NOT_FOUND) {
                throw ex;
            }
        }
        return result;
    }

    public Optional<AccountDto> fallbackFindAccount(String accountId) {
        AddressDto address =  AddressDto.builder().street("Mariano Otero")
                .city("Guadalajara")
                .state("Jalisco")
                .country("Mexico").zipCode("12131")
                .build();

        CustomerDto customer = CustomerDto.builder().lastName("Madero")
                .firstName("Juan")
                .email("juan.made@xyz.com")
                .build();

        CreditCardDto creditCard = CreditCardDto.builder()
                .nameOnCard("Juan Madero")
                .number("4320 1231 4552 1234")
                .expirationMonth("03")
                .expirationYear("2023")
                .type("VISA")
                .ccv("123")
                .build();

        AccountDto account = AccountDto.builder()
                .address(address)
                .customer(customer)
                .creditCard(creditCard)
                .status(AccountStatus.ACTIVE)
                .id(Long.valueOf(accountId))
                .build();

        return  Optional.ofNullable(account);
    }

    public AccountDto createAccount(AccountDto account) {
        return restTemplate.postForObject(config.getCustomerServiceUrl(), account, AccountDto.class);
    }

    public AccountDto createAccountBody(AccountDto account) {
        ResponseEntity<AccountDto> responseAccount = restTemplate.postForEntity(config.getCustomerServiceUrl(),
                                                                                account, AccountDto.class);
        log.info("Response: " +  responseAccount.getHeaders());
        return responseAccount.getBody();
    }

    public void updateAccount(AccountDto account) {
        restTemplate.put(config.getCustomerServiceUrl() + "/{id}", account, account.getId());
    }

    public void deleteAccount(AccountDto account) {
        restTemplate.delete(config.getCustomerServiceUrl() + "/{id}", account.getId());
    }
}
