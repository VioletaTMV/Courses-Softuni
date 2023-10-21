package com.dictionaryapp.controller;

import com.dictionaryapp.model.DTOs.WordAddDTO;
import com.dictionaryapp.service.AuthService;
import com.dictionaryapp.service.WordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WordAddController {

    private WordService wordService;
    private AuthService authService;

    @Autowired
    public WordAddController(WordService wordService, AuthService authService) {
        this.wordService = wordService;
        this.authService = authService;
    }

    @ModelAttribute("wordModel")
    public WordAddDTO initWordAddModel(){

        return new WordAddDTO();
    }

    @GetMapping("/word-add")
    public String addWord() {

        if (!this.authService.userIsLoggedIn()){
            return "redirect:/login";
        }

        return "/word-add";
    }

    @PostMapping("/word-add")
    public String addWord(@Valid WordAddDTO wordAddDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (!this.authService.userIsLoggedIn()){
            return "redirect:/login";
        }

        if (bindingResult.hasErrors() || !this.wordService.create(wordAddDTO)) {

            redirectAttributes.addFlashAttribute("wordModel", wordAddDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.wordModel", bindingResult);

            return "redirect:/word-add";
        }

        return "redirect:/home";
    }
}
