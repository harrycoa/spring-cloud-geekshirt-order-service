package com.geekshirt.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreditCardDto implements Serializable {
    private Long id;
    private String nameOnCard;
    private String number;
    private String type;
    private String expirationMonth;
    private String expirationYear;
    private String ccv;
}
