package dev.arena.product.services.product;

import dev.arena.common.constants.CommonConstants;
import dev.arena.common.dto.PageResponseDTO;
import dev.arena.common.exceptions.NotFoundEntityException;
import dev.arena.common.utils.PageableUtils;
import dev.arena.common.utils.PaginationUtils;
import dev.arena.product.constants.Constants;
import dev.arena.product.dto.product.ProductPurchaseRequestDTO;
import dev.arena.product.dto.product.ProductPurchaseResponseDTO;
import dev.arena.product.dto.product.ProductRequestDTO;
import dev.arena.product.dto.product.ProductResponseDTO;
import dev.arena.product.entities.Category;
import dev.arena.product.entities.Product;
import dev.arena.product.exceptions.ProductPurchaseException;
import dev.arena.product.mappers.product.ProductMapper;
import dev.arena.product.repositories.CategoryRespository;
import dev.arena.product.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImplementation implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRespository categoryRespository;
    private final ProductMapper productMapper;

    @Override
    public PageResponseDTO<ProductResponseDTO> findAllProducts(int page, int size, UriComponentsBuilder uriComponentsBuilder) {

        uriComponentsBuilder.path(Constants.PRODUCT_URL);

        Pageable pageable = PageableUtils.setPageable(page, size, CommonConstants.CREATED_AT);

        Page<Product> productPage = productRepository.findAll(pageable);

        return PaginationUtils.createPageResponse(productPage, productMapper::productResponseDTO, uriComponentsBuilder);
    }

    @Override
    @Transactional
    public ProductResponseDTO saveProduct(ProductRequestDTO productRequestDTO) {

        Product product = productMapper.requestDTOToProduct(productRequestDTO);
        product.setAvailableQuantity(productRequestDTO.available_quantity());

        if (productRequestDTO.category_id() != null) {
            Category category = categoryRespository
                    .findById(productRequestDTO.category_id())
                    .orElseThrow(() -> new NotFoundEntityException("Category is not found!"));
            product.setCategory(category);
        }

        Product savedProduct = productRepository.save(product);
        return productMapper.productResponseDTO(savedProduct);
    }

    private Product findByResourceId(UUID resourceId) {
        return productRepository.findById(resourceId)
                .orElseThrow(() -> new NotFoundEntityException("Product not found with the ID: " + resourceId));
    }

    @Override
    public ProductResponseDTO findById(UUID resourceId) {
        return  productMapper.productResponseDTO(findByResourceId(resourceId));
    }

    @Override
    @Transactional
    public Boolean deleteById(UUID resourceId) {

        if (!productRepository.existsById(resourceId)) {
            return false;
        }

        productRepository.deleteById(resourceId);

        return true;
    }

    @Override
    @Transactional
    public List<ProductPurchaseResponseDTO> purchaseProducts(List<ProductPurchaseRequestDTO> productPurchaseRequestDTOS) {

        List<UUID> productIds = productPurchaseRequestDTOS
                .stream()
                .map(ProductPurchaseRequestDTO::productId)
                .toList();

        List<Product> storedProducts = productRepository.findAllByIdInOrderById(productIds);

        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exists");
        }

        List<ProductPurchaseRequestDTO> storedRequest = productPurchaseRequestDTOS.stream().sorted(Comparator.comparing(ProductPurchaseRequestDTO::productId)).toList();

        List<ProductPurchaseResponseDTO> purchasedProducts = new ArrayList<>();

        for (int i = 0; i < storedProducts.size(); i++) {

            Product product = storedProducts.get(i);
            var productRequest = storedRequest.get(i);

            if (product.getAvailableQuantity() < productRequest.quantity()) {
                throw new ProductPurchaseException("Insufficient stock quantity for product with Id: " + productRequest.productId());
            }

            int newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);

            purchasedProducts.add(productMapper.productPurchaseResponseDTO(product, productRequest.quantity()));

        }

        return purchasedProducts;
    }
}
