package com.example.CarDealer_XML.entities.cars;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportCarsWrapperDTO {

    @XmlElement(name = "car")
    private List<ImportCarsDTO> cars;

    public ImportCarsWrapperDTO(){}

    public ImportCarsWrapperDTO(List<ImportCarsDTO> cars) {
        this.cars = cars;
    }

    public List<ImportCarsDTO> getCars() {
        return cars;
    }

    public void setCars(List<ImportCarsDTO> cars) {
        this.cars = cars;
    }
}
