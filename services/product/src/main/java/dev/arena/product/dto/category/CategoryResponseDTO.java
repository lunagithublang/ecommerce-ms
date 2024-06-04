package dev.arena.product.dto.category;

import dev.arena.common.dto.BaseResponseDTO;
import dev.arena.product.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CategoryResponseDTO extends BaseResponseDTO {

    private String name;
    private String description;
    private List<Product> products;
}

