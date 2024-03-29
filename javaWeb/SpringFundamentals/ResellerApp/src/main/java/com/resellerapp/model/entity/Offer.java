package com.resellerapp.model.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "offers")
//@NamedEntityGraph(name = "graph.Offer.seller",
//        attributeNodes = @NamedAttributeNode("seller"))
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Condition condition;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User seller;

    @ManyToOne(fetch = FetchType.LAZY)
    private User buyer;

    public Offer() {
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Offer setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Offer setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Condition getCondition() {
        return condition;
    }

    public Offer setCondition(Condition condition) {
        this.condition = condition;
        return this;
    }

    public User getSeller() {
        return seller;
    }

    public Offer setSeller(User seller) {
        this.seller = seller;
        return this;
    }

    public User getBuyer() {
        return buyer;
    }

    public Offer setBuyer(User buyer) {
        this.buyer = buyer;
        return this;
    }
}
