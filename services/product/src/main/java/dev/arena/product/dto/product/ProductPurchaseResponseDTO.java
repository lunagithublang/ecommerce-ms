package dev.arena.product.dto.product;

import dev.arena.common.dto.BaseResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ProductPurchaseResponseDTO extends BaseResponseDTO {

    String name;
    String description;
    BigDecimal price;
    int quantity;
}
