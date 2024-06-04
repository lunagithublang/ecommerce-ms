package dev.arena.product.services.category;

import dev.arena.common.constants.CommonConstants;
import dev.arena.common.dto.PageResponseDTO;
import dev.arena.common.utils.PageableUtils;
import dev.arena.common.utils.PaginationUtils;
import dev.arena.product.constants.Constants;
import dev.arena.product.dto.category.CategoryRequestDTO;
import dev.arena.product.dto.category.CategoryResponseDTO;
import dev.arena.product.entities.Category;
import dev.arena.product.mappers.category.CategoryMapper;
import dev.arena.product.repositories.CategoryRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class CategoryServiceImplementation implements CategoryService {

    private final CategoryRespository categoryRespository;
    private final CategoryMapper categoryMapper;

    @Override
    public PageResponseDTO<CategoryResponseDTO> findAllCategories(int page, int size, UriComponentsBuilder uriComponentsBuilder) {

        uriComponentsBuilder.path(Constants.PRODUCT_CATEGORY_URL);

        Pageable pageable = PageableUtils.setPageable(page, size, CommonConstants.CREATED_AT);

        Page<Category> categoryPage = categoryRespository.findAll(pageable);

        return PaginationUtils.createPageResponse(categoryPage, categoryMapper::categoryResponseDTO, uriComponentsBuilder);
    }

    @Override
    public CategoryResponseDTO saveCategory(CategoryRequestDTO categoryRequestDTO) {

        Category category = categoryMapper.requestDTOToCategory(categoryRequestDTO);

        Category savedCategory = categoryRespository.save(category);

        return categoryMapper.categoryResponseDTO(savedCategory);
    }
}
