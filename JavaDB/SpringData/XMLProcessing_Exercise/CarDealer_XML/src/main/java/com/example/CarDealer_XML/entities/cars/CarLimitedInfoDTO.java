package com.example.CarDealer_XML.entities.cars;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jakarta.xml.bind.annotation.*;

import java.io.Serializable;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarLimitedInfoDTO implements Serializable {

    @Expose
    @SerializedName("Make")
    @XmlAttribute(name = "make")
    private String make;

   @Expose
   @SerializedName("Model")
   @XmlAttribute(name = "model")
    private String model;

    @Expose
    @SerializedName("TravelledDistance")
    @XmlAttribute(name = "travelled-distance")
    private Long travelledDistance;

    public CarLimitedInfoDTO(){}

    public CarLimitedInfoDTO(String make, String model, Long travelledDistance) {
        this.make = make;
        this.model = model;
        this.travelledDistance = travelledDistance;
    }



    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }
}
