package dev.arena.product.services.product;

import dev.arena.common.dto.PageResponseDTO;
import dev.arena.product.dto.product.ProductPurchaseRequestDTO;
import dev.arena.product.dto.product.ProductPurchaseResponseDTO;
import dev.arena.product.dto.product.ProductRequestDTO;
import dev.arena.product.dto.product.ProductResponseDTO;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    PageResponseDTO<ProductResponseDTO> findAllProducts(int page, int size, UriComponentsBuilder uriComponentsBuilder);

    ProductResponseDTO saveProduct(ProductRequestDTO productRequestDTO);

    ProductResponseDTO findById(UUID resourceId);

    Boolean deleteById(UUID resourceId);

    List<ProductPurchaseResponseDTO> purchaseProducts(List<ProductPurchaseRequestDTO> productPurchaseRequestDTOS);
}
