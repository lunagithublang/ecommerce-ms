package dev.arena.order.producer;

import dev.arena.order.constants.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProducer {

    private final KafkaTemplate<String, OrderConfirmation> kafkaTemplate;

    public void sendOrderConfirmation(OrderConfirmation orderConfirmation){
        log.info("Sending order confirmation with body <{}>", orderConfirmation);
        Message<OrderConfirmation> message = MessageBuilder
                .withPayload(orderConfirmation)
                .setHeader(KafkaHeaders.TOPIC, Constants.ORDER_TOPIC)
                .build();

        kafkaTemplate.send(message);
    }
}
