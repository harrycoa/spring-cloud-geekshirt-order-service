package com.geekshirt.orderservice.client;

import com.geekshirt.orderservice.config.OrderServiceConfig;
import com.geekshirt.orderservice.dto.Confirmation;
import com.geekshirt.orderservice.dto.LineItem;
import com.geekshirt.orderservice.dto.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class InventoryServiceClient {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderServiceConfig serviceConfig;

    public void updateInventory(List<LineItem> requestItems) {
       ResponseEntity<String> response = restTemplate.postForEntity(
                serviceConfig.getInventoryServiceUrl(), requestItems, String.class);

    }
}
