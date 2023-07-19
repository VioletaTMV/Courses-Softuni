package org.example.XMLprocessing.entities.categories;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

import static org.example.XMLprocessing.constants.ErrorMessages.INVALID_NAME;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportCategoryDTO implements Serializable {

   @XmlElement(name = "name")
    private String name;

    public ImportCategoryDTO() {
    }

    public ImportCategoryDTO(String name) {
        this.name = name;
        validate();
    }

    private void validate() {

        if (this.name.length() < 3 || this.name.length() > 15) {
            throw new IllegalArgumentException(INVALID_NAME);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
