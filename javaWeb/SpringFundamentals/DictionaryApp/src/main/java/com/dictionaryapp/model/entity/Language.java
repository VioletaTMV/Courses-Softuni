package com.dictionaryapp.model.entity;

import com.dictionaryapp.model.enums.LanguageNameEnum;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "languages")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private LanguageNameEnum name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "language")
    private Set<Word> words;

    public Language(){
        this.words = new LinkedHashSet<>();
    }

    public Language(LanguageNameEnum name){
        super();
        this.setName(name);
    }

    public void setDescription(LanguageNameEnum languageName){

        String description = "";

        switch (languageName){
            case GERMAN:
                description = "A West Germanic language, is spoken by over 90 million people worldwide. Known for its complex grammar and compound words, it's the official language of Germany and widely used in Europe.";
                break;
            case SPANISH:
                description = "A Romance language, is spoken by over 460 million people worldwide. It boasts a rich history, diverse dialects, and is known for its melodious sound, making it a global cultural treasure.";
                break;
            case FRENCH:
                description = "A Romance language spoken worldwide, known for its elegance and cultural richness. It's the official language of France and numerous nations, famed for its cuisine, art, and literature.";
                break;
            case ITALIAN:
                description = "A Romance language spoken in Italy and parts of Switzerland, with rich cultural heritage. Known for its melodious sounds, it's a gateway to Italian art, cuisine, and history.";
                break;
        }

        this.description = description;

    }

    public Long getId() {
        return id;
    }

    public LanguageNameEnum getName() {
        return name;
    }

    public Language setName(LanguageNameEnum name) {
        this.name = name;
        this.setDescription(name);
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Language setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<Word> getWords() {
        return words;
    }

    public Language setWords(Set<Word> words) {
        this.words = words;
        return this;
    }
}
