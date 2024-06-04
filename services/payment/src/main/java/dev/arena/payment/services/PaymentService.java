package dev.arena.payment.services;

import dev.arena.payment.dto.PaymentRequestDTO;
import dev.arena.payment.dto.PaymentResponseDTO;

public interface PaymentService {

    PaymentResponseDTO savePayment(PaymentRequestDTO paymentRequestDTO);
}
