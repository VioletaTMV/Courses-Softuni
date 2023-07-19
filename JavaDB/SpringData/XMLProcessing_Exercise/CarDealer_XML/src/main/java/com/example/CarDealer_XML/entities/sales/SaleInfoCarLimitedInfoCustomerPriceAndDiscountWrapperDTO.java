package com.example.CarDealer_XML.entities.sales;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleInfoCarLimitedInfoCustomerPriceAndDiscountWrapperDTO {

    @XmlElement(name = "sale")
    private List<SaleInfoCarLimitedInfoCustomerPriceAndDiscountDTO> sales;

    public SaleInfoCarLimitedInfoCustomerPriceAndDiscountWrapperDTO() {
    }

    public SaleInfoCarLimitedInfoCustomerPriceAndDiscountWrapperDTO(List<SaleInfoCarLimitedInfoCustomerPriceAndDiscountDTO> sales) {
        this.sales = sales;
    }

    public List<SaleInfoCarLimitedInfoCustomerPriceAndDiscountDTO> getSales() {
        return sales;
    }

    public void setSales(List<SaleInfoCarLimitedInfoCustomerPriceAndDiscountDTO> sales) {
        this.sales = sales;
    }
}
