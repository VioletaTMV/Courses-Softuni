package org.example.SoftUniGameStore.domain.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.example.SoftUniGameStore.constants.Validations.*;

/*
Validate the data for editing a game.

Input args [0] and [1] are skipped as they represent already taken command and id.
 */

public class GameEditDTO {

    private String title;
    private BigDecimal price;
    private Float size;
    private String trailer;
    private String thumbnailURL;
    private String description;
    private LocalDate releaseDate;

    public GameEditDTO() {
    }


    public GameEditDTO populateFieldsWithDataToBeEdited(GameEditDTO gameEditDTO, String[] inputArr) {

        int inputArgsLength = inputArr.length;

        for (int i = 2; i < inputArgsLength; i++) {
            String[] currentArgWithValue = inputArr[i].split("=");
            String fieldToEdit = currentArgWithValue[0];
            String valueToUpdate = currentArgWithValue[1];

            switch (fieldToEdit) {
                case "title":
                    setTitle(VALIDATE_GAME_TITLE(valueToUpdate));
                    break;
                case "price":
                    setPrice(VALIDATE_GAME_PRICE(new BigDecimal(valueToUpdate)));
                    break;
                case "size":
                    setSize(VALIDATE_GAME_SIZE(Float.parseFloat(valueToUpdate)));
                    break;
                case "trailer":
                 setTrailer(VALIDATE_GAME_TRAILER(valueToUpdate));
                    break;
                case "thumbnailURL":
                    setThumbnailURL(VALIDATE_GAME_THUMBNAIL_URL(valueToUpdate));
                    break;
                case "description":
                    setDescription(VALIDATE_GAME_DESCRIPTION(valueToUpdate));
                    break;
                case "releaseDate":
                    setReleaseDate(LocalDate.parse(valueToUpdate, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    break;

            }

        }


        return gameEditDTO;
    }


    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    private void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Float getSize() {
        return size;
    }

    private void setSize(Float size) {
        this.size = size;
    }

    public String getTrailer() {
        return trailer;
    }

    private void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    private void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    private void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

}
