package com.example.CarDealer.entities.cars;

import com.example.CarDealer.entities.parts.PartLimitedInfoDTO;
import com.google.gson.annotations.Expose;

import java.util.List;

public class CarWithPartsDTO {

    @Expose
    private CarLimitedInfoDTO car;

    @Expose
    private List<PartLimitedInfoDTO> parts;

    public CarWithPartsDTO(){}

    public CarWithPartsDTO(CarLimitedInfoDTO car, List<PartLimitedInfoDTO> parts) {
        this.car = car;
        this.parts = parts;
    }

    public CarLimitedInfoDTO getCar() {
        return car;
    }

    public void setCar(CarLimitedInfoDTO car) {
        this.car = car;
    }

    public List<PartLimitedInfoDTO> getParts() {
        return parts;
    }

    public void setParts(List<PartLimitedInfoDTO> parts) {
        this.parts = parts;
    }
}
