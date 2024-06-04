package dev.arena.order.controllers;

import dev.arena.common.constants.CommonConstants;
import dev.arena.common.dto.PageResponseDTO;
import dev.arena.order.constants.Constants;
import dev.arena.order.dto.orderline.OrderLineResponseDTO;
import dev.arena.order.services.orderline.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.ORDER_LINE_URL)
public class OrderLineController {

    private final OrderLineService orderLineService;

    @GetMapping("")
    public ResponseEntity<PageResponseDTO<OrderLineResponseDTO>> index(
            @RequestParam(value = "page", defaultValue = CommonConstants.PAGE_SIZE) int page,
            @RequestParam(value = "size", defaultValue = CommonConstants.PAGE_NUMBER) int size,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        return ResponseEntity.ok(orderLineService.findAll(page, size, uriComponentsBuilder));
    }

}


