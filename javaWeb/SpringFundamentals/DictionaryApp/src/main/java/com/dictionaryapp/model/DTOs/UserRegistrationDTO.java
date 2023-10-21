package com.dictionaryapp.model.DTOs;

import com.dictionaryapp.validation.FieldMatch;
import com.dictionaryapp.validation.UniqueUserEmail;
import com.dictionaryapp.validation.UniqueUserName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@FieldMatch(
        first = "password",
        second = "confirmPassword",
        message = "Passwords don't match"
)
public class UserRegistrationDTO {

    @NotBlank
    @Size(min = 3, max = 20)
    @UniqueUserName(message="Username should be unique")
    private String username;

    @NotBlank
    @Email
    @UniqueUserEmail(message = "Email should be unique")
    private String email;

    @NotBlank
    @Size(min = 3, max = 20)
    private String password;

    @NotBlank
    private String confirmPassword;

    public UserRegistrationDTO(){}

    public String getUsername() {
        return username;
    }

    public UserRegistrationDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    @Override
    public String toString() {
        return "UserRegistrationDTO{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + (password != null ? "[PROVIDED]" : null) + '\'' +
                ", confirmPassword='" + (confirmPassword != null ? "[PROVIDED]" : null) + '\'' +
                '}';
    }
}
