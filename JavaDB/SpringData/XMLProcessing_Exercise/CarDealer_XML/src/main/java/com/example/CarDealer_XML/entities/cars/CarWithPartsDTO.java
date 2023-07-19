package com.example.CarDealer_XML.entities.cars;

import com.example.CarDealer_XML.entities.parts.PartLimitedInfoDTO;
import com.google.gson.annotations.Expose;
import jakarta.xml.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarWithPartsDTO implements Serializable {

    @Expose
    @XmlElement(name = "car")
    private CarLimitedInfoDTO car;

    @Expose
    @XmlElement(name = "part")
    @XmlElementWrapper(name = "parts")
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
