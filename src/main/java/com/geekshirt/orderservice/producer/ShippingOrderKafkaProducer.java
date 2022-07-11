package com.geekshirt.orderservice.producer;

import com.geekshirt.orderservice.dto.AccountDto;
import com.geekshirt.orderservice.dto.CustomerDto;
import com.geekshirt.orderservice.dto.ShipmentOrderRequest;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ShippingOrderKafkaProducer {
    @Autowired
    private Source source;

    public void publish(String orderId, AccountDto account) {
        ShipmentOrderRequest shipmentRequest = new ShipmentOrderRequest();

        CustomerDto customer = account.getCustomer();
        String shipmentReceiver = customer.getLastName() + ", " + customer.getFirstName();

        shipmentRequest.setOrderId(orderId);
        shipmentRequest.setName(shipmentReceiver);
        shipmentRequest.setReceiptEmail(customer.getEmail());
        shipmentRequest.setShippingAddress(account.getAddress());

        source.output()
                .send(MessageBuilder
                    .withPayload(shipmentRequest)
                    .build());


        log.debug(" [x] Sent to Kafka '" + shipmentRequest.toString() + "'");
    }
}
