package dev.arena.order.apis;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name="order", url="${application.config.product-url}")
public interface ProductClient {

    @GetMapping("/{resourceId}")
    void findProductById(@PathVariable("resourceId") UUID resourceId);
}
