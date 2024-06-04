package dev.arena.payment.producer;

import dev.arena.payment.constants.Constants;
import dev.arena.payment.dto.PaymentNotificationRequestDTO;
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
public class NotificationProducer {

    private final KafkaTemplate<String, PaymentNotificationRequestDTO> kafkaTemplate;

    public void sendNotification(PaymentNotificationRequestDTO paymentNotificationRequestDTO) {
        log.info("Sending notification with body <{}>", paymentNotificationRequestDTO);

        Message<PaymentNotificationRequestDTO> message = MessageBuilder
                .withPayload(paymentNotificationRequestDTO)
                .setHeader(KafkaHeaders.TOPIC, Constants.PAYMENT_TOPIC)
                .build();
        kafkaTemplate.send(message);
    }
}
