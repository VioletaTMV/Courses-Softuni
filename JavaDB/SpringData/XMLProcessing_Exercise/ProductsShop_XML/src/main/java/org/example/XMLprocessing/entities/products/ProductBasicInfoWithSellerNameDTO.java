package org.example.XMLprocessing.entities.products;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductBasicInfoWithSellerNameDTO implements Serializable {

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "price")
    private BigDecimal price;

    @XmlAttribute(name = "seller")
    private String seller;

    public ProductBasicInfoWithSellerNameDTO(){}

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
