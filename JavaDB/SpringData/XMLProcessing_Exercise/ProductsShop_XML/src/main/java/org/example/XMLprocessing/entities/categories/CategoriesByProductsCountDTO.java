package org.example.XMLprocessing.entities.categories;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesByProductsCountDTO implements Serializable {

    @XmlAttribute(name = "name")
    private String category;
    @XmlElement(name = "products-count")
    private Long productsCount;
    @XmlElement(name = "average-price")
    private Double averagePrice;
    @XmlElement(name = "total-revenue")
    private BigDecimal totalRevenue;

    public CategoriesByProductsCountDTO() {
    }

    public CategoriesByProductsCountDTO(String category, Long productsCount, Double averagePrice, BigDecimal totalRevenue) {
        this.category = category;
        this.productsCount = productsCount;
        this.averagePrice = averagePrice;
        this.totalRevenue = totalRevenue;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String productCategoriesName) {
        this.category = productCategoriesName;
    }

    public Long getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(Long productsCount) {
        this.productsCount = productsCount;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
