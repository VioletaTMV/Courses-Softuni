package org.example.XMLprocessing.entities.categories;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesByProductsCountWrapperDTO implements Serializable {

    @XmlElement(name = "category")
    List<CategoriesByProductsCountDTO> categories;

    public CategoriesByProductsCountWrapperDTO(){}

    public CategoriesByProductsCountWrapperDTO(List<CategoriesByProductsCountDTO> categories) {
        this.categories = categories;
    }

    public List<CategoriesByProductsCountDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriesByProductsCountDTO> categories) {
        this.categories = categories;
    }
}
