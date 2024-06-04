package dev.arena.customer.dto.customers;

import dev.arena.customer.dto.customers.address.CustomerAddressRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CustomerRequestDTO(
         @NotEmpty
         String firstName,
         @NotEmpty
         String lastName,
         @NotEmpty
         @Email
         String email,
         @NotNull
         @Valid
         CustomerAddressRequestDTO address
) {
}
