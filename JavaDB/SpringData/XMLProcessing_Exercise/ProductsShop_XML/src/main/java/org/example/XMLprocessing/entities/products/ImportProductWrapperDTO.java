package org.example.XMLprocessing.entities.products;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportProductWrapperDTO implements Serializable {

    @XmlElement(name = "product")
    private List<ImportProductDTO> products;

    public ImportProductWrapperDTO(){}

    public ImportProductWrapperDTO(List<ImportProductDTO> products) {
        this.products = products;
    }

    public List<ImportProductDTO> getProducts() {
        return products;
    }
}
