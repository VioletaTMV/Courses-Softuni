package org.example.SoftUniGameStore.domain.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "games")
public class Game extends BaseEntity{

    @Column(nullable = false)
    private String title;

    @Column
    private String trailer;

    @Column(name = "image_thumbnail")
    private String imageThumbnail;

    @Basic
    private Short size;

    @Column(nullable = false)
    private BigDecimal price;

    @Column
    private String description;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    public Game(){}

    public Game(String title, Short size, BigDecimal price, LocalDate releaseDate) {
        this.title = title;
        this.size = size;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getImageThumbnail() {
        return imageThumbnail;
    }

    public void setImageThumbnail(String imageThumbnail) {
        this.imageThumbnail = imageThumbnail;
    }

    public Short getSize() {
        return size;
    }

    public void setSize(Short size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
