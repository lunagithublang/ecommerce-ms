package dev.arena.customer.dto.customers;

import dev.arena.customer.dto.customers.address.CustomerAddressPatchRequestDTO;
import jakarta.validation.constraints.Email;

public record CustomerPatchRequestDTO(
        String firstName,
        String lastName,
        @Email
        String email,
        CustomerAddressPatchRequestDTO address
) {
}
