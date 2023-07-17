package org.example.JSONprocessing.entities.products;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SoldProductsWithCountDTO {

    @Expose
    private long count;
    @Expose
    @SerializedName("products")
    private List<SoldProductDTO> soldProducts;

    public SoldProductsWithCountDTO(){}

    public SoldProductsWithCountDTO(List<SoldProductDTO> soldProducts) {
        this.soldProducts = soldProducts;
        this.count = soldProducts.size();
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<SoldProductDTO> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<SoldProductDTO> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
