package com.dictionaryapp.validation;

import com.dictionaryapp.service.AuthService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUserEmailValidator implements ConstraintValidator<UniqueUserEmail, String> {

    private AuthService authService;

    @Autowired
    public UniqueUserEmailValidator(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return this.authService.findByEmail(value).isEmpty();
    }
}
