package com.example.CarDealer_XML.entities.cars;

import com.google.gson.annotations.Expose;
import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportCarsDTO {

    @Expose
    @XmlElement(name = "make")
    private String make;

    @Expose
    @XmlElement(name = "model")
    private String model;

    @Expose
    @XmlElement(name = "travelled-distance")
    private Long travelledDistance;

    public ImportCarsDTO(){}

    public ImportCarsDTO(String make, String model, Long travelledDistance) {
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
