package ru.zagshak.buySupply.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.zagshak.buySupply.service.OfferService;

@Controller
public class HelloController {


    @Autowired
    private OfferService offerService;

    @GetMapping("/")
    public String hello(){
        return "hello";
    }


    @GetMapping("/offers")
    public String offers(Model model){
        model.addAttribute("offers",offerService.getAll());
        return "offers";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
