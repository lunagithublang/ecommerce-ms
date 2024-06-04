package dev.arena.payment.mappers;

import dev.arena.payment.dto.PaymentRequestDTO;
import dev.arena.payment.dto.PaymentResponseDTO;
import dev.arena.payment.entities.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    PaymentResponseDTO paymentResponseDTO(Payment payment);

    Payment paymentRequestToPayment(PaymentRequestDTO paymentRequestDTO);
}
