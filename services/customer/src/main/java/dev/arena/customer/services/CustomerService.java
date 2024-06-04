package dev.arena.customer.services;

import dev.arena.common.dto.PageResponseDTO;
import dev.arena.customer.dto.customers.CustomerPatchRequestDTO;
import dev.arena.customer.dto.customers.CustomerRequestDTO;
import dev.arena.customer.dto.customers.CustomerResponseDTO;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

public interface CustomerService {

    PageResponseDTO<CustomerResponseDTO> findAllCustomers(int page, int size, UriComponentsBuilder uriComponentsBuilder);

    CustomerResponseDTO saveCustomer(CustomerRequestDTO customerRequestDTO);

    CustomerResponseDTO findById(String resourceId);

    CustomerResponseDTO updateById(String resourceId, CustomerRequestDTO customerRequestDTO);

    CustomerResponseDTO patchUpdateById(String resourceId, CustomerPatchRequestDTO customerPatchRequestDTO);

    Boolean deleteById(String resourceId);
}
