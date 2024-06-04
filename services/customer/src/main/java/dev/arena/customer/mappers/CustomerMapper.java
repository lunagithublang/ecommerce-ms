package dev.arena.customer.mappers;

import dev.arena.customer.dto.customers.CustomerPatchRequestDTO;
import dev.arena.customer.dto.customers.CustomerRequestDTO;
import dev.arena.customer.dto.customers.CustomerResponseDTO;
import dev.arena.customer.dto.customers.address.CustomerAddressPatchRequestDTO;
import dev.arena.customer.dto.customers.address.CustomerAddressRequestDTO;
import dev.arena.customer.entities.Address;
import dev.arena.customer.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer customerRequestDTO(CustomerRequestDTO customerRequestDTO);

    CustomerResponseDTO  customerResponseDTO(Customer customer);

    Customer requestCustomerPatchDTOToCustomer(CustomerPatchRequestDTO customerPatchRequestDTO);

    Address requestCustomerDTOtoAddress(CustomerAddressRequestDTO addressRequestDTO);

    Address requestCustomerPatchDTOtoAddress(CustomerAddressPatchRequestDTO customerAddressPatchRequestDTO);
}
