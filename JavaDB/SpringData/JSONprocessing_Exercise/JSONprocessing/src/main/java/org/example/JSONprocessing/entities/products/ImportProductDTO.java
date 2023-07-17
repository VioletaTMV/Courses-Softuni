package org.example.JSONprocessing.entities.products;

import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import org.example.JSONprocessing.entities.categories.Category;
import org.example.JSONprocessing.entities.users.User;

import java.math.BigDecimal;
import java.util.Set;

import static org.example.JSONprocessing.constants.ErrorMessages.INVALID_NAME;

public class ImportProductDTO {

    @Expose
    private String name;
    @Expose
    private BigDecimal price;

    public ImportProductDTO(){}

    public ImportProductDTO(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
        validate();
    }

    private void validate() {
        if (this.name.length() < 3){
            throw new IllegalArgumentException(INVALID_NAME);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
