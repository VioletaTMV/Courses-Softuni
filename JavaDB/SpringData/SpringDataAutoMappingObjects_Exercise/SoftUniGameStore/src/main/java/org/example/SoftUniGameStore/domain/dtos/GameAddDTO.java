package org.example.SoftUniGameStore.domain.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.example.SoftUniGameStore.constants.Validations.*;

/*
Validate the data for adding new game.


 */

public class GameAddDTO {

    private String title;
    private BigDecimal price;
    private Float size;
    private String trailer;
    private String thumbnailURL;
    private String description;
    private LocalDate releaseDate;

    public GameAddDTO() {
    }

    public GameAddDTO(String title, BigDecimal price, Float size, String trailer, String thumbnailURL, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.thumbnailURL = thumbnailURL;
        this.description = description;
        this.releaseDate = releaseDate;
        validate();
    }

    private void validate() {

//        boolean isTitleValid = Pattern.matches(TITLE_PATTERN, title);
//        if (!isTitleValid) {
//            throw new IllegalArgumentException(INVALID_TITLE);
//        }
        VALIDATE_GAME_TITLE(title);

//        if (price.compareTo(BigDecimal.ZERO) < 0) {
//            throw new IllegalArgumentException(INVALID_PRICE);
//        }
        VALIDATE_GAME_PRICE(price);

//        if (size < 0) {
//            throw new IllegalArgumentException(INVALID_SIZE);
//        }
        VALIDATE_GAME_SIZE(size);

//        boolean isTrailerIDLengthValid = trailer.length() == 11;
//        boolean isTrailerURLYouTubeURL = trailer.contains("youtube.com/watch?");
//        if (!isTrailerIDLengthValid && !isTrailerURLYouTubeURL) {
//            throw new IllegalArgumentException(INVALID_URL);
//        }
//        trailer = trailer.substring(trailer.length() - 11);
        VALIDATE_GAME_TRAILER(trailer);

//        boolean isThumbnailValidURL = thumbnailURL.startsWith("http://") || thumbnailURL.startsWith("https://");
//        if (!isThumbnailValidURL) {
//            throw new IllegalArgumentException(INVALID_THUMBNAIL_URL);
//        }
        VALIDATE_GAME_THUMBNAIL_URL(thumbnailURL);

//        boolean isDescriptionValid = description.length() >= 20;
//        if (!isDescriptionValid) {
//            throw new IllegalArgumentException(INVALID_DESCRIPTION);
//        }
        VALIDATE_GAME_DESCRIPTION(description);

    }

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

    public Float getSize() {
        return size;
    }

    public void setSize(Float size) {
        this.size = size;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
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
