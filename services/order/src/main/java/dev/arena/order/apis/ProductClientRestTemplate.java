package dev.arena.order.apis;

import dev.arena.common.exceptions.BusinessException;
import dev.arena.order.dto.product.PurchaseProductRequestDTO;
import dev.arena.order.dto.product.PurchaseProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductClientRestTemplate {

    @Value("${application.config.product-url}")
    private  String productUrl;
    private final RestTemplate restTemplate;

    public List<PurchaseProductResponseDTO> purchaseProducts(List<PurchaseProductRequestDTO> purchaseProductRequestDTO) {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<List<PurchaseProductRequestDTO>> requestEntity =
                new HttpEntity<>(purchaseProductRequestDTO, headers);

        ParameterizedTypeReference<List<PurchaseProductResponseDTO>> responseType = new ParameterizedTypeReference<>() {};

        ResponseEntity<List<PurchaseProductResponseDTO>> responseEntity =
                restTemplate.exchange(
                        productUrl + "/purchase",
                        HttpMethod.POST,
                        requestEntity,
                        responseType
                );

        if (responseEntity.getStatusCode().isError()) {
            throw new BusinessException("An error occurred while processing the product purchase" + responseEntity.getStatusCode());
        }

        return responseEntity.getBody();
    }
}
