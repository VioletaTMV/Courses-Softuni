package org.example.XMLprocessing.entities.products;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.math.BigDecimal;

import static org.example.XMLprocessing.constants.ErrorMessages.INVALID_NAME;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportProductDTO implements Serializable {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "price")
    private BigDecimal price;

    public ImportProductDTO() {
    }

    public ImportProductDTO(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
        validate();
    }

    private void validate() {
        if (this.name.length() < 3) {
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
