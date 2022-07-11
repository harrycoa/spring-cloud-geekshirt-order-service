package com.geekshirt.orderservice.dto;

import com.geekshirt.orderservice.util.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AccountDto implements Serializable {
    private Long id;
    private AddressDto address;
    private CustomerDto customer;
    private CreditCardDto creditCard;
    private AccountStatus status;
}
