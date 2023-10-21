package com.dictionaryapp.init;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.enums.LanguageNameEnum;
import com.dictionaryapp.repo.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LanguageSeeder implements CommandLineRunner {

    private LanguageRepository languageRepository;

    @Autowired
    public LanguageSeeder(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (this.languageRepository.count() == 0){

            List<Language> languageList = Arrays.stream(LanguageNameEnum.values())
                    .map(Language::new)
                    .collect(Collectors.toList());

            this.languageRepository.saveAll(languageList);

        }

    }
}
