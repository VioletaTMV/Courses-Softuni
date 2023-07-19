package org.example.XMLprocessing.entities.products;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class SoldProductsWithCountDTO implements Serializable {

@XmlAttribute(name = "count")
    private long count;
@XmlElement(name = "product")
  //  @SerializedName("products")
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
