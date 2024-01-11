package com.registerApp.registerForm.model;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchingValidator implements ConstraintValidator<PasswordMatching,Object> {
    @Override
    public void initialize(PasswordMatching constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        EmployeeModel employeeModel = (EmployeeModel) object;
        return employeeModel.getPassword().equals(employeeModel.getConfirmPassword());
    }


}
