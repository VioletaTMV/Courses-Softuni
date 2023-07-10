package org.example.SoftUniGameStore.domain.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GameDetailViewDTO {
    private String title;
    private BigDecimal price;
    private String description;
    private LocalDate releaseDate;

    public GameDetailViewDTO(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public String toString() {
        return "Title: " + this.getTitle() + System.lineSeparator() +
                "Price: " + this.getPrice() + System.lineSeparator() +
                "Description: " + this.getDescription() + System.lineSeparator() +
                "ReleaseDate: " + this.getReleaseDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}
