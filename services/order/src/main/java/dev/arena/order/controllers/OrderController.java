package dev.arena.order.controllers;

import dev.arena.common.constants.CommonConstants;
import dev.arena.common.dto.PageResponseDTO;
import dev.arena.order.constants.Constants;
import dev.arena.order.dto.order.OrderRequestDTO;
import dev.arena.order.dto.order.OrderResponseDTO;
import dev.arena.order.services.order.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping(Constants.ORDERS_URL)
@RequiredArgsConstructor
@Validated
public class OrderController {

    private final OrderService orderService;

    @GetMapping("")
    public ResponseEntity<PageResponseDTO<OrderResponseDTO>> index(
            @RequestParam(value ="page", defaultValue = CommonConstants.PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = CommonConstants.PAGE_SIZE) int size,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        return ResponseEntity.ok(orderService.findAll(page, size, uriComponentsBuilder));
    }

    @PostMapping("")
    public ResponseEntity<OrderResponseDTO> saveOrder(@RequestBody @Valid OrderRequestDTO orderRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.saveOrder(orderRequestDTO));
    }


    @DeleteMapping("/{resourceId}")
    public ResponseEntity<Void> destroy(@PathVariable("resourceId") UUID resourceId) {
        if (!orderService.deletedById(resourceId)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.accepted().build();
    }
}
