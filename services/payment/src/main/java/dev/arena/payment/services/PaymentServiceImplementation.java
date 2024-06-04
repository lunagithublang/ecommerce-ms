package dev.arena.payment.services;

import dev.arena.payment.dto.PaymentNotificationRequestDTO;
import dev.arena.payment.dto.PaymentRequestDTO;
import dev.arena.payment.dto.PaymentResponseDTO;
import dev.arena.payment.entities.Payment;
import dev.arena.payment.mappers.PaymentMapper;
import dev.arena.payment.producer.NotificationProducer;
import dev.arena.payment.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImplementation implements PaymentService{

    private final PaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository;
    private final NotificationProducer notificationProducer;

    @Override
    @Transactional
    public PaymentResponseDTO savePayment(PaymentRequestDTO paymentRequestDTO) {

        Payment savedPayment = paymentRepository.save(paymentMapper.paymentRequestToPayment(paymentRequestDTO));

        notificationProducer.sendNotification(
                new PaymentNotificationRequestDTO(
                        paymentRequestDTO.amount(),
                        paymentRequestDTO.paymentMethod(),
                        paymentRequestDTO.orderReference(),
                        paymentRequestDTO.customer().getFirstName(),
                        paymentRequestDTO.customer().getLastName(),
                        paymentRequestDTO.customer().getEmail()
                )
        );

        return paymentMapper.paymentResponseDTO(savedPayment);
    }
}
