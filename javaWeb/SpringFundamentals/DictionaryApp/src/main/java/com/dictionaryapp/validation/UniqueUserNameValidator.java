package com.dictionaryapp.validation;

import com.dictionaryapp.service.AuthService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {

    private AuthService authService;

    @Autowired
    public UniqueUserNameValidator(AuthService authService) {
        this.authService = authService;
    }


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return authService.findByUsername(value).isEmpty();
    }
}
