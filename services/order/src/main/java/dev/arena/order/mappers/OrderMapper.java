package dev.arena.order.mappers;

import dev.arena.order.dto.order.OrderRequestDTO;
import dev.arena.order.dto.order.OrderResponseDTO;
import dev.arena.order.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderResponseDTO orderResponseDTO(Order order);

    Order requestDTOToOrder(OrderRequestDTO orderRequestDTO);
}
