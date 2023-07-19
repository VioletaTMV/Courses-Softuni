package com.example.CarDealer_XML.entities.cars;

import jakarta.xml.bind.annotation.*;

import java.util.List;

//този клас е излишен
@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarWithPartsWrapperDTO {

    @XmlElement(name = "car")
    private List<CarWithPartsDTO> carsWithParts;

    public CarWithPartsWrapperDTO(){}

    public CarWithPartsWrapperDTO(List<CarWithPartsDTO> carsWithParts) {
        this.carsWithParts = carsWithParts;
    }

    public List<CarWithPartsDTO> getCarsWithParts() {
        return carsWithParts;
    }

    public void setCarsWithParts(List<CarWithPartsDTO> carsWithParts) {
        this.carsWithParts = carsWithParts;
    }
}
