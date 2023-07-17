package com.example.CarDealer.entities.cars;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jakarta.persistence.Column;

public class CarLimitedInfoDTO {

    @Expose
    @SerializedName("Make")
    private String make;

   @Expose
   @SerializedName("Model")
    private String model;

    @Expose
    @SerializedName("TravelledDistance")
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
