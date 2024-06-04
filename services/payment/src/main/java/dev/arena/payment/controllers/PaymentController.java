package dev.arena.payment.controllers;

import dev.arena.payment.constants.Constants;
import dev.arena.payment.dto.PaymentRequestDTO;
import dev.arena.payment.dto.PaymentResponseDTO;
import dev.arena.payment.services.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.PAYMENT_URL)
@Validated
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("")
    public String hello () {
        return "hello payment";
    }

    @PostMapping("")
    public ResponseEntity<PaymentResponseDTO> create(@Valid @RequestBody PaymentRequestDTO paymentRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(paymentService.savePayment(paymentRequestDTO));
    }

}
