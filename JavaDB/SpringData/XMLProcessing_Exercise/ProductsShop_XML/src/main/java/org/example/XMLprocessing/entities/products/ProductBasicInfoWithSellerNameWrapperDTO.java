package org.example.XMLprocessing.entities.products;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductBasicInfoWithSellerNameWrapperDTO implements Serializable {

    @XmlElement(name = "product")
    private List<ProductBasicInfoWithSellerNameDTO> products;

    public ProductBasicInfoWithSellerNameWrapperDTO(){}

    public ProductBasicInfoWithSellerNameWrapperDTO(List<ProductBasicInfoWithSellerNameDTO> products) {
        this.products = products;
    }

    public List<ProductBasicInfoWithSellerNameDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductBasicInfoWithSellerNameDTO> products) {
        this.products = products;
    }
}
