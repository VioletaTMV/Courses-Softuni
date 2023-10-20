package com.resellerapp.controller;

import com.resellerapp.model.DTOs.OfferCreateDTO;
import com.resellerapp.service.OfferServiceImpl;
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
public class OfferAddController {

    private OfferServiceImpl offerService;
    private AuthServiceImpl userService;

    @Autowired
    public OfferAddController(OfferServiceImpl offerService, AuthServiceImpl userService) {
        this.offerService = offerService;
        this.userService = userService;
    }

    @ModelAttribute("offerModel")
    public OfferCreateDTO initOfferModel(){

        return new OfferCreateDTO();
    }

    @GetMapping("/offer-add")
    public String addOffer(){

        if (!this.userService.userIsLoggedIn()){
            return "redirect:/login";
        }

        return "offer-add";
    }

    @PostMapping("/offer-add")
    public String addOffer(@Valid OfferCreateDTO offerCreateDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){

        if (!this.userService.userIsLoggedIn()){
            return "redirect:/login";
        }

        if (bindingResult.hasErrors() || !this.offerService.create(offerCreateDTO)){

            redirectAttributes.addFlashAttribute("offerModel", offerCreateDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel", bindingResult);

            return "redirect:/offer-add";
        }


        return "redirect:/home";
    }
}
