package com.dictionaryapp.service.serviceImpl;

import com.dictionaryapp.model.DTOs.WordAddDTO;
import com.dictionaryapp.model.DTOs.WordDTO;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.model.enums.LanguageNameEnum;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.service.LanguageService;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WordServiceImpl implements WordService {

    private WordRepository wordRepository;
    private CurrentUser currentUser;
    private AuthServiceImpl authService;
    private ModelMapper modelMapper;
    private LanguageService languageService;

    @Autowired
    public WordServiceImpl(WordRepository wordRepository, CurrentUser currentUser, AuthServiceImpl authService, ModelMapper modelMapper, LanguageService languageService) {
        this.wordRepository = wordRepository;
        this.currentUser = currentUser;
        this.authService = authService;
        this.modelMapper = modelMapper;
        this.languageService = languageService;
    }

    @Override
    public boolean create(WordAddDTO wordAddDTO) {

        Optional<User> authorOpt = this.authService.findById(this.currentUser.getId());

        if (authorOpt.isEmpty()) {
            return false;
        }

        Word newWord = this.modelMapper.map(wordAddDTO, Word.class)
                .setAddedBy(authorOpt.get())
                .setLanguage(this.languageService.getName(LanguageNameEnum.valueOf(wordAddDTO.getLanguage())));

        this.wordRepository.save(newWord);

        return true;
    }

    @Override
    public List<WordDTO> getWordsByLanguage(LanguageNameEnum nameEnum) {

        List<Word> wordsByLanguage = this.wordRepository.findAllByLanguageId(this.languageService.getName(nameEnum).getId());

        List<WordDTO> wordsByLanguageDTOs = wordsByLanguage.stream()
                .map(w -> new WordDTO(w).setAddedBy(this.currentUser.getUsername()))
                .collect(Collectors.toList());


        return wordsByLanguageDTOs;
    }

    @Override
    public void deleteWord(Long id) {

        Optional<Word> wordToDelete = this.wordRepository.findById(id);

        if (!wordToDelete.isPresent()){
            throw new NoSuchElementException();
        }

        this.wordRepository.delete(wordToDelete.get());
    }

    @Override
    public List<WordDTO> findAll() {

        List<Word> all = this.wordRepository.findAll();

        List<WordDTO> allDTOs = all.stream()
                .map(w -> new WordDTO(w).setAddedBy(this.currentUser.getUsername()))
                .collect(Collectors.toList());

        return allDTOs;

    }
}
