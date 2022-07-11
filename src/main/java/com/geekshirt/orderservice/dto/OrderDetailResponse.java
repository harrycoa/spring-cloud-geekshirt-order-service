package com.geekshirt.orderservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class OrderDetailResponse implements Serializable {
    private long id;
    private int quantity;
    private double price;
    private double tax;
    private String upc;
    private Double totalAmount;
}
