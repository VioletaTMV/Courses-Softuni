package com.example.CarDealer.entities.cars;

import com.example.CarDealer.entities.parts.PartLimitedInfoDTO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarLimitedInfoWithPartsLimitInfoDTO {
    @Expose
    @SerializedName("Make")
    private String make;

    @Expose
    @SerializedName("Model")
    private String model;

    @Expose
    @SerializedName("TravelledDistance")
    private Long travelledDistance;

    @Expose
    private List<PartLimitedInfoDTO> parts;

    public CarLimitedInfoWithPartsLimitInfoDTO(){}

    public CarLimitedInfoWithPartsLimitInfoDTO(String make, String model, Long travelledDistance) {
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

    public List<PartLimitedInfoDTO> getParts() {
        return parts;
    }

    public void setParts(List<PartLimitedInfoDTO> parts) {
        this.parts = parts;
    }
}
