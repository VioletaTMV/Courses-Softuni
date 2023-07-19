package org.example.XMLprocessing.entities.categories;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportCategoryWrapperDTO implements Serializable {

@XmlElement(name = "category")
    private List<ImportCategoryDTO> categories;

public ImportCategoryWrapperDTO(){}

    public ImportCategoryWrapperDTO(List<ImportCategoryDTO> categories) {
        this.categories = categories;
    }

    public List<ImportCategoryDTO> getCategories() {
        return categories;
    }
}
