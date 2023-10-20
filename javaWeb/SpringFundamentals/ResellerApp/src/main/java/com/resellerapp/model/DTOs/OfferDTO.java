package com.resellerapp.model.DTOs;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;

import javax.persistence.*;
import java.math.BigDecimal;

public class OfferDTO {


    private Long id;


    private String description;


    private BigDecimal price;


    private String condition;


    private String sellerUsername;


    private String buyerUsername;

    public OfferDTO(){}

    public OfferDTO(Offer offer){

        this.id = offer.getId();
        this.description = offer.getDescription();
        this.price = offer.getPrice();
        this.condition = offer.getCondition().getName().toString();
        this.sellerUsername = offer.getSeller().getUsername();
    }

    public Long getId() {
        return id;
    }

    public OfferDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getCondition() {
        return condition;
    }

    public OfferDTO setCondition(String condition) {
        this.condition = condition;
        return this;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public OfferDTO setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
        return this;
    }

    public String getBuyerUsername() {
        return buyerUsername;
    }

    public OfferDTO setBuyerUsername(String buyerUsername) {
        this.buyerUsername = buyerUsername;
        return this;
    }
}
