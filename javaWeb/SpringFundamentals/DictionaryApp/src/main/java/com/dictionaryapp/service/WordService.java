package com.dictionaryapp.service;

import com.dictionaryapp.model.DTOs.WordAddDTO;
import com.dictionaryapp.model.DTOs.WordDTO;
import com.dictionaryapp.model.enums.LanguageNameEnum;

import java.util.List;

public interface WordService {
    boolean create(WordAddDTO wordAddDTO);

    List<WordDTO> getWordsByLanguage(LanguageNameEnum nameEnum);

    void deleteWord(Long id);

    List<WordDTO> findAll();
}
