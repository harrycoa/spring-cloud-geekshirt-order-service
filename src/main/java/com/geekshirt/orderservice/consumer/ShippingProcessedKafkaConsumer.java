package com.geekshirt.orderservice.consumer;

import com.geekshirt.orderservice.dto.ShipmentOrderResponse;
import com.geekshirt.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ShippingProcessedKafkaConsumer {

    @Autowired
    private OrderService orderService;

    @StreamListener(Sink.INPUT)
    public void receive(final ShipmentOrderResponse in) {
        log.info(" [x] Received Shipment Information: {}'", in);
        log.info(" [x] Received Shipment Information - Tracking ID: {}'", in.getTrackingId());
        log.info(" [x] Received Shipment Information - Status: {}'", in.getShippingStatus());
        orderService.updateShipmentOrder(in);
    }
}
