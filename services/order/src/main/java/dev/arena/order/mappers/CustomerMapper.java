package dev.arena.order.mappers;

import dev.arena.order.dto.customer.CustomerResponseDTO;
import dev.arena.order.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerResponseDTO customerResponseDTO(Customer customer);
}
