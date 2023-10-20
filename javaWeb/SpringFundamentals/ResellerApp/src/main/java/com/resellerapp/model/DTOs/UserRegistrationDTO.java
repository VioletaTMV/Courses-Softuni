package com.resellerapp.model.DTOs;

import com.resellerapp.model.entity.User;
import com.resellerapp.vallidation.FieldMatch;
import com.resellerapp.vallidation.UniqueUserEmail;
import com.resellerapp.vallidation.UniqueUserName;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@FieldMatch(
        first = "password",
        second = "confirmPassword",
        message = "Passwords don't match"
)
public class UserRegistrationDTO implements Serializable {

    @Size(min = 3, max = 20)
    @NotBlank
    @UniqueUserName(message="Username should be unique")
    private String username;

    @Email
    @NotBlank
    @UniqueUserEmail(message="Email should be unique")
    private String email;

    @Size(min = 3, max = 20)
    @NotBlank
    private String password;


    private String confirmPassword;

    public UserRegistrationDTO() {
    }

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

