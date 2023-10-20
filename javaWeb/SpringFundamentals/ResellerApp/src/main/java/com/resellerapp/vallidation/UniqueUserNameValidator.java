package com.resellerapp.vallidation;

import com.resellerapp.service.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {

    private AuthServiceImpl userService;

    @Autowired
    public UniqueUserNameValidator(AuthServiceImpl userService) {
        this.userService = userService;
    }


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return userService.findByUsername(s).isEmpty();
    }
}
