package org.example.JSONprocessing.entities.users;

import com.google.gson.annotations.SerializedName;
import org.example.JSONprocessing.entities.products.ProductsSold;

import java.util.List;

public class UserWithProductsSoldAndBuyerInfoDTO {

    private String firstName;
    private String lastName;
    @SerializedName("soldProducts")
    private List<ProductsSold> productsSold;

    public UserWithProductsSoldAndBuyerInfoDTO(){}

    public UserWithProductsSoldAndBuyerInfoDTO(String firstName, String lastName, List<ProductsSold> productsSold) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.productsSold = productsSold;

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

    public List<ProductsSold> getProductsSold() {
        return productsSold;
    }

    public void setProductsSold(List<ProductsSold> productsSold) {
        this.productsSold = productsSold;
    }
}
