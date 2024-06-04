package dev.arena.payment.dto;

import dev.arena.common.dto.BaseResponseDTO;
import dev.arena.payment.enums.PaymentMethod;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentResponseDTO extends BaseResponseDTO {

    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private UUID orderId;
}
