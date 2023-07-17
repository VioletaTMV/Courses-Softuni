package org.example.JSONprocessing.entities.products;

import java.math.BigDecimal;

public class ProductBasicInfoWithSellerNameDTO {

    private String name;
    private BigDecimal price;
    private String seller;

    public ProductBasicInfoWithSellerNameDTO(String name, BigDecimal price, String seller) {
        this.name = name;
        this.price = price;
        this.seller = seller;
    }

    public static ProductBasicInfoWithSellerNameDTO getFromProduct(Product product){
        String fullName;
        if (product.getSeller().getFirstName() == null){
            fullName = product.getSeller().getLastName();
        } else {
            fullName = product.getSeller().getFirstName() + " " + product.getSeller().getLastName();
        }

        return new ProductBasicInfoWithSellerNameDTO(product.getName(), product.getPrice(), fullName);
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

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}
