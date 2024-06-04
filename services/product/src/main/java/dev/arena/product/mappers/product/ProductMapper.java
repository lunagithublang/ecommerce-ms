package dev.arena.product.mappers.product;

import dev.arena.product.dto.product.ProductPurchaseRequestDTO;
import dev.arena.product.dto.product.ProductPurchaseResponseDTO;
import dev.arena.product.dto.product.ProductRequestDTO;
import dev.arena.product.dto.product.ProductResponseDTO;
import dev.arena.product.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductResponseDTO productResponseDTO(Product product);

    ProductPurchaseResponseDTO productPurchaseResponseDTO(Product product, int quantity);

    Product requestDTOToProduct(ProductRequestDTO productRequestDTO);

    Product requestProductPurchaseDTOToProduct(ProductPurchaseRequestDTO productPurchaseRequestDTO);
}
