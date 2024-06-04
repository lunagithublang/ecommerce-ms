package dev.arena.customer.services;

import dev.arena.common.dto.PageResponseDTO;
import dev.arena.common.utils.PageableUtils;
import dev.arena.common.utils.PaginationUtils;
import dev.arena.common.constants.CommonConstants;
import dev.arena.customer.constants.Constants;
import dev.arena.customer.dto.customers.CustomerPatchRequestDTO;
import dev.arena.customer.dto.customers.CustomerRequestDTO;
import dev.arena.customer.dto.customers.CustomerResponseDTO;
import dev.arena.customer.entities.Address;
import dev.arena.customer.entities.Customer;
import dev.arena.customer.exceptions.CustomerNotFoundException;
import dev.arena.customer.mappers.CustomerMapper;
import dev.arena.customer.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomerServiceImplementation implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CustomerAddressService customerAddressService;

    @Override
    public PageResponseDTO<CustomerResponseDTO> findAllCustomers(int page, int size, UriComponentsBuilder uriComponentsBuilder) {

        uriComponentsBuilder.path(Constants.CUSTOMER_URL);

        Pageable pageable = PageableUtils.setPageable(page, size, CommonConstants.CREATED_AT);

        Page<Customer> customersPage = customerRepository.findAll(pageable);

        return PaginationUtils.createPageResponse(customersPage, customerMapper::customerResponseDTO, uriComponentsBuilder);
    }

    @Override
    @Transactional
    public CustomerResponseDTO saveCustomer(CustomerRequestDTO customerRequestDTO) {

        Customer customer = customerMapper.customerRequestDTO(customerRequestDTO);
        customer.setCreatedAt(LocalDateTime.now());

        customerRepository.save(customer);

        return customerMapper.customerResponseDTO(customer);
    }

    @Override
    public CustomerResponseDTO findById(String resourceId) {
        return customerMapper.customerResponseDTO(findByCustomerId(resourceId));
    }

    @Override
    @Transactional
    public CustomerResponseDTO updateById(String resourceId, CustomerRequestDTO customerRequestDTO) {

        Customer existingCustomer = findByCustomerId(resourceId);

        existingCustomer.setFirstName(customerRequestDTO.firstName());
        existingCustomer.setLastName(customerRequestDTO.lastName());
        existingCustomer.setEmail(customerRequestDTO.email());
        existingCustomer.setAddress(customerAddressService.buildAddress(customerRequestDTO));
        existingCustomer.setUpdatedAt(LocalDateTime.now());

        customerRepository.save(existingCustomer);

        return customerMapper.customerResponseDTO(existingCustomer);
    }

    @Override
    @Transactional
    public CustomerResponseDTO patchUpdateById(String resourceId, CustomerPatchRequestDTO customerPatchRequestDTO) {

        Customer existingCustomer = findByCustomerId(resourceId);

        Address address = customerAddressService.getPatchAddress(customerPatchRequestDTO, existingCustomer.getAddress());

        existingCustomer.setAddress(address);
        
        if (customerPatchRequestDTO.firstName() != null) {
            existingCustomer.setFirstName(customerPatchRequestDTO.firstName());
        }

        if (customerPatchRequestDTO.lastName() != null) {
            existingCustomer.setLastName(customerPatchRequestDTO.lastName());
        }

        if (customerPatchRequestDTO.email() != null) {
            existingCustomer.setEmail(customerPatchRequestDTO.email());
        }
        if (customerPatchRequestDTO.email() != null) {
            existingCustomer.setEmail(customerPatchRequestDTO.email());
        }

        existingCustomer.setUpdatedAt(LocalDateTime.now());

        customerRepository.save(existingCustomer);

        return customerMapper.customerResponseDTO(existingCustomer);
    }

    @Override
    @Transactional
    public Boolean deleteById(String resourceId) {

        if (!customerRepository.existsById(resourceId)) {
            return false;
        }

        customerRepository.deleteById(resourceId);

        return true;
    }

    private Customer findByCustomerId(String resourceId) {
        return customerRepository.findById(resourceId).orElseThrow(() -> new CustomerNotFoundException(
                String.format("No customer found with the provided ID ", resourceId)
        ));
    }
}
