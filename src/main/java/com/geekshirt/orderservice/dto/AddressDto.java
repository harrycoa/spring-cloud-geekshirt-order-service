package com.geekshirt.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AddressDto implements Serializable {
    private Long id;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}
