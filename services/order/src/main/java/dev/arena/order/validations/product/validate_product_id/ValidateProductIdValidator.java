package dev.arena.order.validations.product.validate_product_id;

import dev.arena.order.apis.ProductClient;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class ValidateProductIdValidator implements ConstraintValidator<ValidateProductId, UUID> {

    private final ProductClient productClient;

    @Override
    public void initialize(ValidateProductId constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UUID productId, ConstraintValidatorContext context) {

        try {
            productClient.findProductById(productId);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
