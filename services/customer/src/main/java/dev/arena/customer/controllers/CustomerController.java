package dev.arena.customer.controllers;

import dev.arena.common.dto.PageResponseDTO;
import dev.arena.customer.constants.Constants;
import dev.arena.common.constants.CommonConstants;
import dev.arena.customer.dto.customers.CustomerPatchRequestDTO;
import dev.arena.customer.dto.customers.CustomerRequestDTO;
import dev.arena.customer.dto.customers.CustomerResponseDTO;
import dev.arena.customer.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(Constants.CUSTOMER_URL)
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("")
    public ResponseEntity<PageResponseDTO<CustomerResponseDTO>> index(
            @RequestParam(value = "page", defaultValue = CommonConstants.PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = CommonConstants.PAGE_SIZE) int size,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        return ResponseEntity.ok(customerService.findAllCustomers(page, size, uriComponentsBuilder));
    }

    @PostMapping("")
    public ResponseEntity<CustomerResponseDTO> store(@Valid @RequestBody CustomerRequestDTO customerRequestDTO) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerService.saveCustomer(customerRequestDTO));
    }

    @GetMapping("/{resourceId}")
    public ResponseEntity<CustomerResponseDTO> show(@PathVariable("resourceId") String resourceId) {
        return ResponseEntity.ok(customerService.findById(resourceId));
    }

    @PutMapping("/{resourceId}")
    public ResponseEntity<CustomerResponseDTO> update(
            @PathVariable("resourceId") String resourceId,
            @RequestBody @Valid CustomerRequestDTO customerRequestDTO
    ) {
        return ResponseEntity.ok(customerService.updateById(resourceId, customerRequestDTO));
    }

    @PatchMapping("/{resourceId}")
    public ResponseEntity<CustomerResponseDTO> patch(
            @PathVariable("resourceId") String resourceId,
            @RequestBody @Valid CustomerPatchRequestDTO customerPatchRequestDTO
    ) {
        return ResponseEntity.ok(customerService.patchUpdateById(resourceId, customerPatchRequestDTO));
    }

    @DeleteMapping("/{resourceId}")
    public ResponseEntity<Void> destroy(@PathVariable("resourceId") String resourceId) {
        if (!customerService.deleteById(resourceId)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.accepted().build();
    }
}
