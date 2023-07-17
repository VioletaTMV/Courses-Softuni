package org.example.JSONprocessing.entities.products;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class SoldProductDTO {
    @Expose
    private String name;
    @Expose
    private BigDecimal price;

    public SoldProductDTO(){};

    public SoldProductDTO(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
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
