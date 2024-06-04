package dev.arena.customer.dto.customers.address;


public record CustomerAddressPatchRequestDTO(
        String street,
        String houseNumber,
        String zipCode
) {
}
