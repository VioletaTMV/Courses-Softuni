package org.example.SoftUniGameStore.domain.dtos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.example.SoftUniGameStore.constants.ErrorMessages.*;
import static org.example.SoftUniGameStore.constants.Validations.*;

public class  UserRegisterDTO {

    private String email;
    private String password;
    private String confirmPassword;
    private String fullName;

    public UserRegisterDTO(){}

    public UserRegisterDTO(String email, String password, String confirmPassword, String fullName) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
        validate();
    }

    private void validate() {

        boolean isEmailValid = false;
        boolean isPasswordValid = false;
        boolean isConfirmPasswordValid = false;

        isEmailValid = Pattern.matches(EMAIL_PATTERN, email);
        if (!isEmailValid) {
            throw new IllegalArgumentException(INVALID_EMAIL);
        }

        Pattern regexPassword = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcherPassword = regexPassword.matcher(password);

        isPasswordValid = matcherPassword.find();
        if (!isPasswordValid) {
            throw new IllegalArgumentException(INVALID_PASSWORD);
        }

        isConfirmPasswordValid = password.equals(confirmPassword);
        if (!isConfirmPasswordValid) {
            throw new IllegalArgumentException(PASS_MISMATCH);
        }

    }

    public String successfullyRegisteredUser(){
        return fullName + " was registered.";
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
