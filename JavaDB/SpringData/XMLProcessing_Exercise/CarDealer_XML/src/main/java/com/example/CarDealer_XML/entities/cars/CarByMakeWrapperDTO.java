package com.example.CarDealer_XML.entities.cars;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarByMakeWrapperDTO implements Serializable {

    @XmlElement(name = "car")
    private List<CarByMakeDTO> cars;

    public CarByMakeWrapperDTO(){}

    public CarByMakeWrapperDTO(List<CarByMakeDTO> cars) {
        this.cars = cars;
    }

    public List<CarByMakeDTO> getCars() {
        return cars;
    }

    public void setCars(List<CarByMakeDTO> cars) {
        this.cars = cars;
    }
}
