package dev.arena.order.apis;

import dev.arena.order.dto.payment.PaymentRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name= "payment", url = "${application.config.payment-url}")
public interface PaymentClient {

    @PostMapping("")
     void requestOrderPayment(@RequestBody PaymentRequestDTO paymentRequestDTO);
}
