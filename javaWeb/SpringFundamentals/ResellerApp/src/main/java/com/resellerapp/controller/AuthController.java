package com.resellerapp.controller;

import com.resellerapp.model.DTOs.UserLoginDTO;
import com.resellerapp.model.DTOs.UserRegistrationDTO;
import com.resellerapp.service.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AuthController {

    private AuthServiceImpl authService;

    @Autowired
    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @ModelAttribute("userRegistrationModel")
    public UserRegistrationDTO initUserRegistrationModel() {
        return new UserRegistrationDTO();
    }

    @ModelAttribute("userLoginModel")
    public UserLoginDTO initUserLoginModel(){
        return new UserLoginDTO();
    }


    @GetMapping("/register")
    public String register() {

        if (this.authService.userIsLoggedIn()){
            return "redirect:/home";
        }

        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationDTO userRegistrationDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (this.authService.userIsLoggedIn()){
            return "redirect:/home";
        }

        if (bindingResult.hasErrors() ) {
            redirectAttributes.addFlashAttribute("userRegistrationModel", userRegistrationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationModel", bindingResult);

            return "redirect:/register";
        }

        this.authService.register(userRegistrationDTO);

        return "redirect:/login";
    }


    @GetMapping("/login")
    public String login() {

        if (this.authService.userIsLoggedIn()){
            return "redirect:/home";
        }

        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginDTO userLoginDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes){

        if (this.authService.userIsLoggedIn()){
            return "redirect:/home";
        }

       if (bindingResult.hasErrors()){

           redirectAttributes.addFlashAttribute("userLoginModel", userLoginDTO);
           redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginModel", bindingResult);

           return "redirect:/login";
       }


       if (!this.authService.login(userLoginDTO)){

           redirectAttributes.addFlashAttribute("userLoginModel", userLoginDTO);
           redirectAttributes.addFlashAttribute("badCredentials", true);

           return "redirect:/login";

       }

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(){

        if (!this.authService.userIsLoggedIn()){
            return "redirect:/";
        }

        this.authService.logout();

        return "redirect:/";
    }
}
