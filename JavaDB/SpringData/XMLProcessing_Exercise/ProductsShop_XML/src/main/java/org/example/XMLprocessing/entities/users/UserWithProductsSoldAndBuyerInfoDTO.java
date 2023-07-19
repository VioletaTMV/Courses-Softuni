package org.example.XMLprocessing.entities.users;

import jakarta.xml.bind.annotation.*;
import org.example.XMLprocessing.entities.products.ProductsSold;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithProductsSoldAndBuyerInfoDTO implements Serializable {

    @XmlAttribute(name = "first-name")
    private String firstName;
    @XmlAttribute(name = "last-name")
    private String lastName;
   // @SerializedName("soldProducts")
    @XmlElement(name = "product")
    @XmlElementWrapper(name = "sold-products")
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
