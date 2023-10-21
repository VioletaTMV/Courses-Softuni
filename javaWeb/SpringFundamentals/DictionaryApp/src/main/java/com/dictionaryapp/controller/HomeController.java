package com.dictionaryapp.controller;

import com.dictionaryapp.model.DTOs.WordDTO;
import com.dictionaryapp.model.enums.LanguageNameEnum;
import com.dictionaryapp.service.AuthService;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private WordService wordService;
    private CurrentUser currentUser;
    private AuthService authService;

    @Autowired
    public HomeController(WordService wordService, CurrentUser currentUser, AuthService authService) {
        this.wordService = wordService;
        this.currentUser = currentUser;
        this.authService = authService;
    }

    @GetMapping("/")
    public String loggedOutIndex() {

        if (this.authService.userIsLoggedIn()){
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String loggedInHome(Model model){

        if (!this.authService.userIsLoggedIn()){
            return "redirect:/";
        }


        List<WordDTO> germanWordDTOS = this.wordService.getWordsByLanguage(LanguageNameEnum.GERMAN);
        List<WordDTO> frenchWordDTOS = this.wordService.getWordsByLanguage(LanguageNameEnum.FRENCH);
        List<WordDTO> spanishWordDTOS = this.wordService.getWordsByLanguage(LanguageNameEnum.SPANISH);
        List<WordDTO> italianWordDTOS = this.wordService.getWordsByLanguage(LanguageNameEnum.ITALIAN);
        int totalWordsCount = germanWordDTOS.size() + frenchWordDTOS.size() + spanishWordDTOS.size() + italianWordDTOS.size();

        model.addAttribute("germanWords", germanWordDTOS);
        model.addAttribute("frenchWords", frenchWordDTOS);
        model.addAttribute("spanishWords", spanishWordDTOS);
        model.addAttribute("italianWords", italianWordDTOS);
        model.addAttribute("totalWords", totalWordsCount);


        return "home";
    }
}
