package com.geekshirt.orderservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class OrderResponse implements Serializable {
    private String orderId;
    private String status;
    private String accountId;
    private Double totalAmount;
    private Double totalTax;
    private Double totalAmountTax;
    private Date transactionDate;

    private List<OrderDetailResponse> details;
}
