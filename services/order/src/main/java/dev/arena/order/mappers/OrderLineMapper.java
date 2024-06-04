package dev.arena.order.mappers;

import dev.arena.order.dto.orderline.OrderLineRequestDTO;
import dev.arena.order.dto.orderline.OrderLineResponseDTO;
import dev.arena.order.entities.OrderLine;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderLineMapper {
    OrderLineMapper INSTANCE = Mappers.getMapper(OrderLineMapper.class);

    OrderLineResponseDTO orderLineResponseDTO(OrderLine orderLine);

    OrderLine requestDTOtoOrderLine(OrderLineRequestDTO orderLineRequestDTO);
}
