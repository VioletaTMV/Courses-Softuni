package com.resellerapp.model.DTOs;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.enums.ConditionNameEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class OfferCreateDTO {

    @NotBlank
    @Size(min = 2, max = 50)
    private String description;

    @NotNull
    private BigDecimal price;

    @NotBlank
    private String condition;

    public OfferCreateDTO() {
    }

    public String getDescription() {
        return description;
    }

    public OfferCreateDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferCreateDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getCondition() {
        return condition;
    }

    public OfferCreateDTO setCondition(String condition) {
        this.condition = condition;
        return this;
    }

    @Override
    public String toString() {
        return "OfferCreateDTO{" +
                "description='" + description + '\'' +
                ", price=" + price +
                ", condition=" + condition +
                '}';
    }
}
