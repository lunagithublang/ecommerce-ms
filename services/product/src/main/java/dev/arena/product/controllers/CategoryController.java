package dev.arena.product.controllers;

import dev.arena.common.constants.CommonConstants;
import dev.arena.common.dto.PageResponseDTO;
import dev.arena.product.constants.Constants;
import dev.arena.product.dto.category.CategoryRequestDTO;
import dev.arena.product.dto.category.CategoryResponseDTO;
import dev.arena.product.services.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(Constants.PRODUCT_CATEGORY_URL)
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<PageResponseDTO<CategoryResponseDTO>> index(
            @RequestParam(value = "page", defaultValue = CommonConstants.PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = CommonConstants.PAGE_SIZE) int size,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        return ResponseEntity.ok(categoryService.findAllCategories(page, size, uriComponentsBuilder));
    }

    @PostMapping("")
    public ResponseEntity<CategoryResponseDTO> store(@RequestBody CategoryRequestDTO categoryRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.saveCategory(categoryRequestDTO));
    }

}
