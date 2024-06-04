package dev.arena.product.dto.product;

import dev.arena.common.dto.BaseResponseDTO;
import dev.arena.product.entities.Category;
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
public class ProductResponseDTO extends BaseResponseDTO {

    private String name;
    private String description;
    private int availableQuantity;
    private BigDecimal price;
    private Category category;

}
