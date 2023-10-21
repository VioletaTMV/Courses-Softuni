package com.dictionaryapp.controller;

import com.dictionaryapp.model.DTOs.WordDTO;
import com.dictionaryapp.model.enums.LanguageNameEnum;
import com.dictionaryapp.service.AuthService;
import com.dictionaryapp.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WordActionsController {

    private WordService wordService;
    private AuthService authService;

    @Autowired
    public WordActionsController(WordService wordService, AuthService authService) {
        this.wordService = wordService;
        this.authService = authService;
    }

    @GetMapping("/word/remove")
    public String remove(@RequestParam(value = "id") Long id,
                         Model model) {

        model.addAttribute("id", id);

        if (!this.authService.userIsLoggedIn()) {
            return "redirect:/login";
        }

        try {
            this.wordService.deleteWord(id);
        } catch (IllegalArgumentException e){
            return "redirect:/home";
        }

        return "redirect:/home";
    }

    @GetMapping("/home/remove-all")
    public String removeAll(Model model) {

        if (!this.authService.userIsLoggedIn()) {
            return "redirect:/login";
        }

        List<WordDTO> allWordsDTOS = this.wordService.findAll();

        model.addAttribute("all", allWordsDTOS);

        try {
            allWordsDTOS.stream()
                    .forEach(w -> wordService.deleteWord(w.getId()));
        } catch (IllegalArgumentException e){
            return "redirect:/home";
        }

        return "redirect:/home";

    }
}
