package io.github.x4297.manboapigateway.annotation;


import io.github.x4297.manboapigateway.validator.AllNotEmptyValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Constraint(validatedBy={AllNotEmptyValidator.class})
public @interface AllNotEmpty{

    String message() default "has empty filed";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
