package dev.arena.product.controllers;

import dev.arena.common.constants.CommonConstants;
import dev.arena.common.dto.PageResponseDTO;
import dev.arena.product.constants.Constants;
import dev.arena.product.dto.product.ProductPurchaseRequestDTO;
import dev.arena.product.dto.product.ProductPurchaseResponseDTO;
import dev.arena.product.dto.product.ProductRequestDTO;
import dev.arena.product.dto.product.ProductResponseDTO;
import dev.arena.product.services.product.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(Constants.PRODUCT_URL)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping("")
    public ResponseEntity<PageResponseDTO<ProductResponseDTO>> index(
            @RequestParam(value = "page", defaultValue = CommonConstants.PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = CommonConstants.PAGE_SIZE) int size,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        return ResponseEntity.ok(productService.findAllProducts(page, size, uriComponentsBuilder));
    }

    @PostMapping("")
    public ResponseEntity<ProductResponseDTO> store(@RequestBody @Valid ProductRequestDTO productRequestDTO) {
        return  ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(productRequestDTO));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponseDTO>> purchaseProducts(
            @RequestBody @Valid List<ProductPurchaseRequestDTO> productPurchaseRequestDTOS
    ) {
        return ResponseEntity.ok(productService.purchaseProducts(productPurchaseRequestDTOS));
    }

    @GetMapping("/{resourceId}")
    public ResponseEntity<ProductResponseDTO> show(@PathVariable("resourceId") UUID resourceId) {
        return ResponseEntity.ok(productService.findById(resourceId));
    }


    @DeleteMapping("/{resourceId}")
    public ResponseEntity<Void> destroy(@PathVariable("resourceId") UUID resourceId) {

        if (!productService.deleteById(resourceId)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.accepted().build();
    }
}
