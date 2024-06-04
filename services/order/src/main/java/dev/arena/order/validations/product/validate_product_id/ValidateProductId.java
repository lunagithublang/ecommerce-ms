package dev.arena.order.validations.product.validate_product_id;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidateProductIdValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateProductId {

    String message() default "Product ID not found!";

    Class<?>[] groups() default  {};

    Class<? extends Payload>[] payload() default {};
}
