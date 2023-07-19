package org.example.XMLprocessing.entities.users;

import jakarta.xml.bind.annotation.*;
import org.example.XMLprocessing.entities.products.SoldProductsWithCountDTO;

import java.io.Serializable;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersWithProductsSoldDTO implements Serializable {

@XmlAttribute(name = "first-name")
    private String firstName;
@XmlAttribute(name = "last-name")
    private String lastName;
@XmlAttribute(name = "age")
    private int age;

    //@SerializedName("soldProducts")
    @XmlElement(name = "sold-products")
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
