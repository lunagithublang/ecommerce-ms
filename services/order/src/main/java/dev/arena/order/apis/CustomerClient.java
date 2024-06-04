package dev.arena.order.apis;

import dev.arena.order.dto.customer.CustomerResponseDTO;
import dev.arena.order.entities.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name= "customer", url = "${application.config.customer-url}")
public interface CustomerClient {

    @GetMapping("/{resourceId}")
    Optional<CustomerResponseDTO> findCustomerById(@PathVariable("resourceId") String resourceId);
//    Customer findCustomerById(@PathVariable("resourceId") String resourceId);

}
