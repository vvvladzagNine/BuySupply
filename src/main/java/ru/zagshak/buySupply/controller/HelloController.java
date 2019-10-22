package ru.zagshak.buySupply.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zagshak.buySupply.service.EstimateService;
import ru.zagshak.buySupply.service.OfferService;
import ru.zagshak.buySupply.service.RequestService;
import ru.zagshak.buySupply.service.UserService;

@Controller
public class HelloController {


    @Autowired
    private RequestService requestService;

    @Autowired
    private EstimateService estimateService;

    @Autowired
    private OfferService offerService;

    @Autowired
    private UserService userService;

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

    @GetMapping("/profile/{userId}")
    public String profile(@PathVariable int userId, Model model){

        model.addAttribute("user",userService.get(userId));
        model.addAttribute("estimates",estimateService.getAllByEstimated(userId));
        return "profile";
    }


    @GetMapping("/profile/{userId}/requests")
    public String requests(@PathVariable String userId, Model model){


        //TODO персональные реквесты
        model.addAttribute("requests",requestService.getAll());
        return "requests";
    }

}
