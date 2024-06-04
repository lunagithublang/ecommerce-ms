package dev.arena.order.services.orderline;

import dev.arena.common.dto.PageResponseDTO;
import dev.arena.order.dto.orderline.OrderLineRequestDTO;
import dev.arena.order.dto.orderline.OrderLineResponseDTO;
import dev.arena.order.entities.OrderLine;
import org.springframework.web.util.UriComponentsBuilder;

public interface OrderLineService {

    PageResponseDTO<OrderLineResponseDTO> findAll(int page, int size, UriComponentsBuilder uriComponentsBuilder);

    OrderLine saveOrderLine(OrderLineRequestDTO orderLineRequestDTO);
}
