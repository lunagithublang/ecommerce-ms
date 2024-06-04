package dev.arena.customer.services;

import dev.arena.customer.dto.customers.CustomerPatchRequestDTO;
import dev.arena.customer.dto.customers.CustomerRequestDTO;
import dev.arena.customer.dto.customers.address.CustomerAddressPatchRequestDTO;
import dev.arena.customer.entities.Address;
import dev.arena.customer.mappers.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerAddressService {

    private final CustomerMapper customerMapper;

     Address buildAddress(CustomerRequestDTO customerRequestDTO) {
         return customerMapper.requestCustomerDTOtoAddress(customerRequestDTO.address());
    }

    Address getPatchAddress(CustomerPatchRequestDTO customerPatchRequestDTO, Address existingCustomerAddress) {

        CustomerAddressPatchRequestDTO customerAddressPatchRequestDTO = customerPatchRequestDTO.address();

        if (customerAddressPatchRequestDTO.street() != null) {
            existingCustomerAddress.setStreet(customerAddressPatchRequestDTO.street());
        }

        if (customerAddressPatchRequestDTO.zipCode() != null) {
            existingCustomerAddress.setZipCode(customerAddressPatchRequestDTO.zipCode());
        }

        if (customerAddressPatchRequestDTO.houseNumber() != null) {
            existingCustomerAddress.setHouseNumber(customerAddressPatchRequestDTO.houseNumber());
        }

        return existingCustomerAddress;
    }
}
