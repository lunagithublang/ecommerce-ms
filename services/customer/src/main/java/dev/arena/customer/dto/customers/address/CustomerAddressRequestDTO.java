package dev.arena.customer.dto.customers.address;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public record CustomerAddressRequestDTO(
         @NotNull
         @NotEmpty
         String street,
         @NotNull
         @NotEmpty
         String houseNumber,
         @NotNull
         @NotEmpty
         String zipCode
) {
}
