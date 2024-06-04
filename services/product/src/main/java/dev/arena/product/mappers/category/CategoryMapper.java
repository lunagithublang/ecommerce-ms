package dev.arena.product.mappers.category;

import dev.arena.product.dto.category.CategoryRequestDTO;
import dev.arena.product.dto.category.CategoryResponseDTO;
import dev.arena.product.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryResponseDTO categoryResponseDTO(Category category);

    Category requestDTOToCategory(CategoryRequestDTO categoryRequestDTO);
}
