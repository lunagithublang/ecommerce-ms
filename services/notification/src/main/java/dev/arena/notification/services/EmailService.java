package dev.arena.notification.services;

import dev.arena.notification.entities.Product;
import jakarta.mail.MessagingException;
import java.math.BigDecimal;
import java.util.List;

public interface EmailService {

    void sentPaymentSuccessEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference
    ) throws MessagingException;

    void sentOrderConfirmationsEmail(
            String destinationEmail,
            String customerName,
            BigDecimal totalAmount,
            String orderReference,
            List<Product> products
    ) throws MessagingException;
}
