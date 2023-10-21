package com.dictionaryapp.model.DTOs;

import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.service.LanguageService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class WordDTO {

    private Long id;
    private String term;
    private String translation;
    private String example;
    private LocalDate inputDate;
//    private String language;
    private String addedBy;


    public WordDTO(){}

    public WordDTO(Word word){
        this.id = word.getId();
        this.term = word.getTerm();
        this.translation = word.getTranslation();
        this.example = word.getExample();
        this.inputDate = word.getInputDate();
//        this.language = word.getLanguage().toString();
    }

    public Long getId() {
        return id;
    }

    public WordDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTerm() {
        return term;
    }

    public WordDTO setTerm(String term) {
        this.term = term;
        return this;
    }

    public String getTranslation() {
        return translation;
    }

    public WordDTO setTranslation(String translation) {
        this.translation = translation;
        return this;
    }

    public String getExample() {
        return example;
    }

    public WordDTO setExample(String example) {
        this.example = example;
        return this;
    }

    public LocalDate getInputDate() {
        return inputDate;
    }

    public WordDTO setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
        return this;
    }

//    public String getLanguage() {
//        return language;
//    }
//
//    public WordDTO setLanguage(String language) {
//        this.language = language;
//        return this;
//    }

    public String getAddedBy() {
        return addedBy;
    }

    public WordDTO setAddedBy(String addedBy) {
        this.addedBy = addedBy;
        return this;
    }
}
