package org.example.JSONprocessing.entities.users;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.example.JSONprocessing.entities.products.SoldProductsWithCountDTO;

import java.util.List;

public class UsersWithProductsSoldDTO {

    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private int age;
    @Expose
    @SerializedName("soldProducts")
    SoldProductsWithCountDTO soldProductsWithCountDTO;

    public UsersWithProductsSoldDTO() {
    }

    public UsersWithProductsSoldDTO(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public UsersWithProductsSoldDTO(String firstName, String lastName, int age, SoldProductsWithCountDTO soldProductsWithCountDTOList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.soldProductsWithCountDTO = soldProductsWithCountDTOList;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public SoldProductsWithCountDTO getSoldProductsWrapperDTOList() {
        return soldProductsWithCountDTO;
    }

    public void setSoldProductsWrapperDTO(SoldProductsWithCountDTO soldProductsWithCountDTO) {
        this.soldProductsWithCountDTO = soldProductsWithCountDTO;
    }
}
