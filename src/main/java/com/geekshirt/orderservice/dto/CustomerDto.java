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
public class CustomerDto implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
