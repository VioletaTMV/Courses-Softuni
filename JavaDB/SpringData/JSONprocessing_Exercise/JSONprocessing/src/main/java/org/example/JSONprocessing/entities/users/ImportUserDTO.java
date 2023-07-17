package org.example.JSONprocessing.entities.users;

import com.google.gson.annotations.Expose;
import jakarta.persistence.Column;
import org.example.JSONprocessing.constants.ErrorMessages;

import static org.example.JSONprocessing.constants.ErrorMessages.INVALID_NAME;

public class ImportUserDTO {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private Integer age;

    public ImportUserDTO(){}

    public ImportUserDTO(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        validate();
    }

    private void validate() {

        if (this.lastName.length() < 3){
            throw new IllegalArgumentException(INVALID_NAME);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
