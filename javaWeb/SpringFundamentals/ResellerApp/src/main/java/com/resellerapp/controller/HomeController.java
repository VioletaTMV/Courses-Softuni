package com.resellerapp.controller;

import com.resellerapp.model.DTOs.OfferDTO;
import com.resellerapp.service.OfferServiceImpl;
import com.resellerapp.service.AuthServiceImpl;
import com.resellerapp.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private OfferServiceImpl offerService;
    private CurrentUser currentUser;
    private AuthServiceImpl userService;

    @Autowired
    public HomeController(OfferServiceImpl offerService, CurrentUser currentUser, AuthServiceImpl userService) {
        this.offerService = offerService;
        this.currentUser = currentUser;
        this.userService = userService;
    }

    @GetMapping("/")
    public String loggedOutIndex() {

        if (this.userService.userIsLoggedIn()){
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String loggedInHome(Model model) {

        if (!this.userService.userIsLoggedIn()){
            return "redirect:/";
        }

        List<OfferDTO> ownOffers = this.offerService.getOwnedOffers(this.currentUser.getId());
        List<OfferDTO> boughtOffers = this.offerService.getBoughtOffers(this.currentUser.getId());
        List<OfferDTO> othersOffers = this.offerService.getOthersOffers(this.currentUser.getId());

        model.addAttribute("ownOffers", ownOffers);
        model.addAttribute("boughtOffersModel", boughtOffers);
        model.addAttribute("othersOffersModel", othersOffers);

        return "home";
    }
}
