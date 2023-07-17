package org.example.JSONprocessing.entities.categories;

import com.google.gson.annotations.Expose;

import static org.example.JSONprocessing.constants.ErrorMessages.INVALID_NAME;

public class ImportCategoryDTO {

    @Expose
    private String name;

    public ImportCategoryDTO() {
    }

    public ImportCategoryDTO(String name) {
        this.name = name;
        validate();
    }

    private void validate() {

        if (this.name.length() < 3 || this.name.length() > 15) {
            throw new IllegalArgumentException(INVALID_NAME);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
