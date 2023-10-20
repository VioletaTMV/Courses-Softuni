package com.resellerapp.controller;

import com.resellerapp.service.OfferServiceImpl;
import com.resellerapp.service.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OfferActionsController {

    private OfferServiceImpl offerService;
    private AuthServiceImpl userService;

    @Autowired
    public OfferActionsController(OfferServiceImpl offerService, AuthServiceImpl userService) {
        this.offerService = offerService;
        this.userService = userService;
    }

    @PostMapping("/offer/remove")
    public String remove(@RequestParam(value = "id") Long id,
                         Model model){

        model.addAttribute("id", id);

        if (!this.userService.userIsLoggedIn()) {
            return "redirect:/login";
        }

        try {
            this.offerService.deleteOffer(id);
        } catch (IllegalArgumentException e){
            return "redirect:/home";
        }

        return "redirect:/home";

    }

    @PostMapping("/offer/buy")
    public String buy(@RequestParam(value = "id") Long id,
                    Model model) {

        model.addAttribute("id", id);

        if (!this.userService.userIsLoggedIn()) {
            return "redirect:/login";
        }

        this.offerService.updateOfferWithBuyer(id);

        return "redirect:/home";
    }

}
