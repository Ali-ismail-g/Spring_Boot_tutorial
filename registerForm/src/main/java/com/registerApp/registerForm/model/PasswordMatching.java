package com.registerApp.registerForm.model;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchingValidator.class)
@Documented
public @interface PasswordMatching {
    String message() default "Password & Confirm Password don't match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
