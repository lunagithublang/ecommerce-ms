package dev.arena.notification.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.arena.notification.constants.Constants;
import dev.arena.notification.entities.Customer;
import dev.arena.notification.entities.Notification;
import dev.arena.notification.enums.NotificationType;
import dev.arena.notification.kafka.order.OrderConfirmation;
import dev.arena.notification.kafka.payment.PaymentConfirmation;
import dev.arena.notification.repositories.NotificationRepository;
import dev.arena.notification.services.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = Constants.ORDER_TOPIC, groupId = Constants.ORDER_GROUP)
    public void consumeOrderSuccessNotification(@Payload String payload) throws MessagingException, JsonProcessingException {

        OrderConfirmation orderConfirmation = objectMapper.readValue(payload, OrderConfirmation.class);
        log.info(String.format("Consuming the message from order-topic %s", orderConfirmation));

        Notification notification = Notification.builder()
                .notificationType(NotificationType.ORDER_CONFIRMATION)
                .orderConfirmation(orderConfirmation)
                .build();

        notificationRepository.save(notification);

        Customer customer = orderConfirmation.customer();

        String customerName = customer.getFirstName() + " " + customer.getLastName();

        // Send email
        emailService.sentOrderConfirmationsEmail(
                customer.getEmail().trim(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }

    @KafkaListener(topics = Constants.PAYMENT_TOPIC, groupId = Constants.PAYMENT_GROUP)
    public void consumePaymentSuccessNotification(@Payload String payload) throws MessagingException, JsonProcessingException {

        PaymentConfirmation paymentConfirmation = objectMapper.readValue(payload, PaymentConfirmation.class);
        log.info(String.format("Consuming the message from payment-topic %s", paymentConfirmation));

        Notification notification = Notification.builder()
                .notificationType(NotificationType.PAYMENT_CONFIRMATION)
                .paymentConfirmation(paymentConfirmation)
                .build();

        notificationRepository.save(notification);

        String customerName = paymentConfirmation.customerFirstName() + " " + paymentConfirmation.customerLastName();

        // Send email
        emailService.sentPaymentSuccessEmail(
                paymentConfirmation.customerEmail().trim(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );
    }
}
