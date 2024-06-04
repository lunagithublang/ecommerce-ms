package dev.arena.product.services.category;

import dev.arena.common.dto.PageResponseDTO;
import dev.arena.product.dto.category.CategoryRequestDTO;
import dev.arena.product.dto.category.CategoryResponseDTO;
import org.springframework.web.util.UriComponentsBuilder;

public interface CategoryService {
    PageResponseDTO<CategoryResponseDTO> findAllCategories(int page, int size, UriComponentsBuilder uriComponentsBuilder);

    CategoryResponseDTO saveCategory(CategoryRequestDTO categoryRequestDTO);
}
