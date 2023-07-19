package com.example.CarDealer_XML.entities.parts;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportPartWrapperDTO implements Serializable {

    @XmlElement(name = "part")
    private List<ImportPartDTO> parts;

    public ImportPartWrapperDTO(){}

    public ImportPartWrapperDTO(List<ImportPartDTO> parts) {
        this.parts = parts;
    }

    public List<ImportPartDTO> getParts() {
        return parts;
    }

    public void setParts(List<ImportPartDTO> parts) {
        this.parts = parts;
    }
}
