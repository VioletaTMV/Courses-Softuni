package org.example.SoftUniGameStore.constants;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import static org.example.SoftUniGameStore.constants.ErrorMessages.*;

public class Validations {
    public final static String EMAIL_PATTERN = "\\w+@\\w+.{1}\\w+";
    public final static String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[A-Za-z0-9]{6,}$";
    public final static String TITLE_PATTERN = "^[A-Z].{2,100}$";

    public static String VALIDATE_GAME_TITLE(String inputTitle) {
        boolean isTitleValid = Pattern.matches(TITLE_PATTERN, inputTitle);
        if (!isTitleValid) {
            throw new IllegalArgumentException(INVALID_TITLE);
        }
        return inputTitle;
    }

    public static BigDecimal VALIDATE_GAME_PRICE(BigDecimal inputPrice) {
        if (inputPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(INVALID_PRICE);
        }
        return inputPrice;
    }

    public static Float VALIDATE_GAME_SIZE(Float inputSize){
        if (inputSize < 0) {
            throw new IllegalArgumentException(INVALID_SIZE);
        }
        return inputSize;
    }

    public static String VALIDATE_GAME_TRAILER(String inputTrailer){
        boolean isTrailerIDLengthValid = inputTrailer.length() == 11;
        boolean isTrailerURLYouTubeURL = inputTrailer.contains("youtube.com/watch?");
        if (!isTrailerIDLengthValid && !isTrailerURLYouTubeURL) {
            throw new IllegalArgumentException(INVALID_URL);
        }
        String trailerID = inputTrailer.substring(inputTrailer.length() - 11);
        return trailerID;
    }

    public static String VALIDATE_GAME_THUMBNAIL_URL(String inputThumbnailURL){
        boolean isThumbnailValidURL = inputThumbnailURL.startsWith("http://") || inputThumbnailURL.startsWith("https://");
        if (!isThumbnailValidURL) {
            throw new IllegalArgumentException(INVALID_THUMBNAIL_URL);
        }
        return inputThumbnailURL;
    }

    public static String VALIDATE_GAME_DESCRIPTION(String inputDescription){
        boolean isDescriptionValid = inputDescription.length() >= 20;
        if (!isDescriptionValid) {
            throw new IllegalArgumentException(INVALID_DESCRIPTION);
        }
        return inputDescription;
    }
}
