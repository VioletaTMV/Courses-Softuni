package com.example.CarDealer_XML.entities.parts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement(name = "part")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartLimitedInfoDTO implements Serializable {

    @Expose
    @SerializedName("Name")
    @XmlAttribute(name = "name")
    private String name;

   @Expose
   @SerializedName("Price")
   @XmlAttribute(name = "price")
    private BigDecimal price;

   public PartLimitedInfoDTO(){}

    public PartLimitedInfoDTO(String name, BigDecimal price) {
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
