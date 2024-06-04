package dev.arena.notification.entities;

import dev.arena.notification.enums.NotificationType;

import dev.arena.notification.kafka.order.OrderConfirmation;
import dev.arena.notification.kafka.payment.PaymentConfirmation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;



@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "notifications")
@SuperBuilder
public class Notification extends BaseEntity {
    private NotificationType notificationType;
    private PaymentConfirmation paymentConfirmation;
    private OrderConfirmation orderConfirmation;
}
