package org.example.SoftUniGameStore.domain.dtos;

import static org.example.SoftUniGameStore.constants.ErrorMessages.INCORRECT_USERNAME_PASSWORD;

public class UserLoginDTO {

    private String email;
    private String password;

    public UserLoginDTO(){}

    public UserLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public void validate(String realPassword){
        if (!this.password.equals(realPassword)){
            throw new IllegalArgumentException(INCORRECT_USERNAME_PASSWORD);
        }
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
}
